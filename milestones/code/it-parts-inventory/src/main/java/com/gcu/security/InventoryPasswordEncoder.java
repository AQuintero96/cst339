package com.gcu.security;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Encodes and verifies passwords using the existing inventory credential format.
 * Existing registered accounts remain compatible with Spring Security.
 */
public class InventoryPasswordEncoder implements PasswordEncoder {

    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int ITERATIONS = 600000;
    private static final int SALT_LENGTH = 16;
    private static final int HASH_LENGTH = 256;

    private final SecureRandom secureRandom = new SecureRandom();

    /**
     * Creates a salted password hash for account registration.
     *
     * @param rawPassword submitted password
     * @return algorithm, iterations, salt, and hash in the existing format
     */
    @Override
    public String encode(CharSequence rawPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("Password must not be null.");
        }

        byte[] salt = new byte[SALT_LENGTH];
        secureRandom.nextBytes(salt);
        byte[] hash = deriveHash(rawPassword, salt);

        try {
            return ALGORITHM + "$" + ITERATIONS + "$"
                    + Base64.getEncoder().encodeToString(salt) + "$"
                    + Base64.getEncoder().encodeToString(hash);
        } finally {
            Arrays.fill(hash, (byte) 0);
        }
    }

    /**
     * Verifies a submitted password against stored credentials.
     *
     * @param rawPassword submitted password
     * @param encodedPassword stored credential value
     * @return true when the password matches
     */
    @Override
    public boolean matches(
            CharSequence rawPassword, String encodedPassword) {

        if (rawPassword == null || encodedPassword == null) {
            return false;
        }

        String[] fields = encodedPassword.split("\\$", -1);

        if (fields.length != 4 || !ALGORITHM.equals(fields[0])) {
            return false;
        }

        byte[] expectedHash = null;
        byte[] submittedHash = null;

        try {
            // Accepts only the parameters supported by existing accounts.
            if (Integer.parseInt(fields[1]) != ITERATIONS) {
                return false;
            }

            byte[] salt = Base64.getDecoder().decode(fields[2]);
            expectedHash = Base64.getDecoder().decode(fields[3]);

            if (salt.length != SALT_LENGTH
                    || expectedHash.length != HASH_LENGTH / 8) {
                return false;
            }

            submittedHash = deriveHash(rawPassword, salt);
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
     * Derives a password hash and clears temporary password characters.
     *
     * @param password submitted password
     * @param salt account-specific salt
     * @return derived password hash
     */
    private byte[] deriveHash(CharSequence password, byte[] salt) {
        char[] characters = new char[password.length()];

        for (int index = 0; index < password.length(); index++) {
            characters[index] = password.charAt(index);
        }

        PBEKeySpec specification =
                new PBEKeySpec(characters, salt, ITERATIONS, HASH_LENGTH);

        try {
            return SecretKeyFactory.getInstance(ALGORITHM)
                    .generateSecret(specification).getEncoded();
        } catch (GeneralSecurityException exception) {
            throw new IllegalStateException(
                    "Password processing is unavailable.", exception);
        } finally {
            specification.clearPassword();
            Arrays.fill(characters, '\0');
        }
    }
}