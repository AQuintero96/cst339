package com.gcu.model;

/**
 * Holds the stored account values needed for authentication.
 * Plaintext passwords are never included.
 */
public class AccountRecord {

    private final Long userId;
    private final String username;
    private final String firstName;
    private final String passwordHash;

    /**
     * Creates an account record from database values.
     *
     * @param userId the database identifier
     * @param username the registered username
     * @param firstName the user's display name
     * @param passwordHash the encoded password credentials
     */
    public AccountRecord(Long userId, String username,
                         String firstName, String passwordHash) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.passwordHash = passwordHash;
    }

    /** Returns the database identifier. */
    public Long getUserId() {
        return userId;
    }

    /** Returns the registered username. */
    public String getUsername() {
        return username;
    }

    /** Returns the user's first name. */
    public String getFirstName() {
        return firstName;
    }

    /** Returns the encoded password credentials. */
    public String getPasswordHash() {
        return passwordHash;
    }
}