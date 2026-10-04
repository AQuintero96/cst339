package com.gcu.business;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.gcu.data.AccountDataAccessInterface;
import com.gcu.model.AccountRecord;
import com.gcu.model.SessionUser;
import com.gcu.model.UserModel;

/**
 * Registers and authenticates database accounts using salted password hashes.
 */
@Service
public class AccountService implements AccountServiceInterface {

    private static final int SALT_LENGTH = 16;
    private static final int HASH_LENGTH = 256;
    private static final int HASH_ITERATIONS = 600000;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";

    private final AccountDataAccessInterface accountDataService;
    private final SecureRandom secureRandom = new SecureRandom();

    /**
     * Receives the account DAO through constructor injection.
     *
     * @param accountDataService the account persistence service
     */
    public AccountService(AccountDataAccessInterface accountDataService) {
        this.accountDataService = accountDataService;
    }

    /**
     * Stores a validated registration with encoded password credentials.
     *
     * @param user the validated registration information
     * @return true when created, or false for a duplicate username
     */
    @Override
    public boolean register(UserModel user) {
        String encodedPassword = encodePassword(user.getPassword());

        try {
            accountDataService.create(user, encodedPassword);
            return true;
        } catch (DuplicateKeyException exception) {
            // The database unique constraint decides whether a name is taken.
            return false;
        }
    }

    /**
     * Checks submitted credentials against the stored account.
     *
     * @param username the submitted username
     * @param password the submitted password
     * @return session information when authentication succeeds
     */
    @Override
    public Optional<SessionUser> authenticate(
            String username, String password) {

        if (username == null || password == null) {
            return Optional.empty();
        }

        Optional<AccountRecord> result =
                accountDataService.findByUsername(username);

        if (!result.isPresent()) {
            return Optional.empty();
        }

        AccountRecord account = result.get();

        if (!passwordMatches(password, account.getPasswordHash())) {
            return Optional.empty();
        }

        // Password credentials remain outside the user's HTTP session.
        return Optional.of(new SessionUser(
                account.getUsername(), account.getFirstName()));
    }

    /**
     * Encodes the algorithm, iteration count, salt, and hash in one value.
     *
     * @param password the submitted password
     * @return credentials suitable for the password_hash column
     */
    private String encodePassword(String password) {
        byte[] salt = new byte[SALT_LENGTH];
        secureRandom.nextBytes(salt);

        byte[] hash = hashPassword(password, salt, HASH_ITERATIONS);

        try {
            return ALGORITHM + "$" + HASH_ITERATIONS + "$"
                    + Base64.getEncoder().encodeToString(salt) + "$"
                    + Base64.getEncoder().encodeToString(hash);
        } finally {
            Arrays.fill(hash, (byte) 0);
        }
    }

    /**
     * Verifies a password using the supported stored credential format.
     *
     * @param password the submitted password
     * @param encodedPassword the stored credential value
     * @return true when the password matches
     */
    private boolean passwordMatches(
            String password, String encodedPassword) {

        if (encodedPassword == null) {
            return false;
        }

        String[] fields = encodedPassword.split("\\$", -1);

        if (fields.length != 4 || !ALGORITHM.equals(fields[0])) {
            return false;
        }

        byte[] expectedHash = null;
        byte[] submittedHash = null;

        try {
            int iterations = Integer.parseInt(fields[1]);

            // Accepts only the parameters supported by this implementation.
            if (iterations != HASH_ITERATIONS) {
                return false;
            }

            byte[] salt = Base64.getDecoder().decode(fields[2]);
            expectedHash = Base64.getDecoder().decode(fields[3]);

            if (salt.length != SALT_LENGTH
                    || expectedHash.length != HASH_LENGTH / 8) {
                return false;
            }

            submittedHash = hashPassword(password, salt, iterations);
            return MessageDigest.isEqual(expectedHash, submittedHash);
        } catch (IllegalArgumentException exception) {
            // Malformed stored credentials cannot authenticate a user.
            return false;
        } finally {
            if (expectedHash != null) {
                Arrays.fill(expectedHash, (byte) 0);
            }

            if (submittedHash != null) {
                Arrays.fill(submittedHash, (byte) 0);
            }
        }
    }

    /**
     * Derives a password hash using PBKDF2.
     *
     * @param password the submitted password
     * @param salt the account's random salt
     * @param iterations the number of derivation iterations
     * @return the derived hash
     */
    private byte[] hashPassword(
            String password, byte[] salt, int iterations) {

        char[] characters = password.toCharArray();
        PBEKeySpec specification = new PBEKeySpec(
                characters, salt, iterations, HASH_LENGTH);

        try {
            SecretKeyFactory factory =
                    SecretKeyFactory.getInstance(ALGORITHM);

            return factory.generateSecret(specification).getEncoded();
        } catch (GeneralSecurityException exception) {
            throw new IllegalStateException(
                    "Password processing is unavailable.", exception);
        } finally {
            specification.clearPassword();
            Arrays.fill(characters, '\0');
        }
    }
}