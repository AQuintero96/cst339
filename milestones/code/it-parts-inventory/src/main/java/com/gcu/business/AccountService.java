package com.gcu.business;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.stereotype.Service;

import com.gcu.model.SessionUser;
import com.gcu.model.UserModel;

/**
 * Implements account operations using temporary in-memory storage.
 * Accounts are discarded when the application restarts.
 */
@Service
public class AccountService implements AccountServiceInterface {

    private static final int SALT_LENGTH = 16;
    private static final int HASH_LENGTH = 256;
    private static final int HASH_ITERATIONS = 600000;

    private final Map<String, AccountRecord> accounts =
            new ConcurrentHashMap<>();

    private final SecureRandom secureRandom = new SecureRandom();

    /**
     * Registers a validated user when the username is available.
     *
     * @param user the validated registration form
     * @return true when created, or false for a duplicate username
     */
    @Override
    public boolean register(UserModel user) {
        String key = normalizeUsername(user.getUsername());

        if (accounts.containsKey(key)) {
            return false;
        }

        byte[] salt = new byte[SALT_LENGTH];
        secureRandom.nextBytes(salt);

        byte[] passwordHash = hashPassword(user.getPassword(), salt);

        AccountRecord account = new AccountRecord(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getUsername(),
                salt,
                passwordHash);

        // Prevents concurrent requests from creating the same username.
        return accounts.putIfAbsent(key, account) == null;
    }

    /**
     * Checks credentials against the temporary account records.
     *
     * @param username the submitted username
     * @param password the submitted password
     * @return the session user when credentials match
     */
    @Override
    public Optional<SessionUser> authenticate(
            String username, String password) {

        if (username == null || password == null) {
            return Optional.empty();
        }

        AccountRecord account = accounts.get(normalizeUsername(username));

        if (account == null) {
            return Optional.empty();
        }

        byte[] submittedHash = hashPassword(password, account.salt);

        try {
            if (!MessageDigest.isEqual(
                    account.passwordHash, submittedHash)) {
                return Optional.empty();
            }

            return Optional.of(
                    new SessionUser(account.username, account.firstName));
        } finally {
            Arrays.fill(submittedHash, (byte) 0);
        }
    }

    /**
     * Creates a consistent key for case-insensitive username lookup.
     *
     * @param username the submitted username
     * @return the normalized username
     */
    private String normalizeUsername(String username) {
        return username.trim().toLowerCase(Locale.ROOT);
    }

    /**
     * Creates a salted password hash.
     *
     * @param password the submitted password
     * @param salt the account's random salt
     * @return the derived password hash
     */
    private byte[] hashPassword(String password, byte[] salt) {
        char[] characters = password.toCharArray();

        PBEKeySpec specification = new PBEKeySpec(
                characters,
                salt,
                HASH_ITERATIONS,
                HASH_LENGTH);

        try {
            SecretKeyFactory factory =
                    SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            return factory.generateSecret(specification).getEncoded();
        } catch (GeneralSecurityException exception) {
            throw new IllegalStateException(
                    "Password processing is unavailable.", exception);
        } finally {
            specification.clearPassword();
            Arrays.fill(characters, '\0');
        }
    }

    /**
     * Stores registration details and hashed credentials in memory.
     * Password confirmation and plaintext passwords are not retained.
     */
    private static final class AccountRecord {

        private final String firstName;
        private final String lastName;
        private final String email;
        private final String phoneNumber;
        private final String username;
        private final byte[] salt;
        private final byte[] passwordHash;

        /**
         * Creates a temporary account record.
         *
         * @param firstName the registered first name
         * @param lastName the registered last name
         * @param email the registered email address
         * @param phoneNumber the registered phone number
         * @param username the registered username
         * @param salt the random password salt
         * @param passwordHash the derived password hash
         */
        private AccountRecord(
                String firstName,
                String lastName,
                String email,
                String phoneNumber,
                String username,
                byte[] salt,
                byte[] passwordHash) {

            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.username = username;
            this.salt = salt;
            this.passwordHash = passwordHash;
        }
    }
}