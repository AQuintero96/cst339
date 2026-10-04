package com.gcu.data;

import java.util.Optional;

import com.gcu.model.AccountRecord;
import com.gcu.model.UserModel;

/**
 * Defines the database operations required for registration and login.
 */
public interface AccountDataAccessInterface {

    /**
     * Inserts a validated registration with encoded password credentials.
     *
     * @param user the validated registration information
     * @param passwordHash the encoded password hash
     */
    void create(UserModel user, String passwordHash);

    /**
     * Looks up the stored credentials for a username.
     *
     * @param username the username to find
     * @return the matching account, or an empty result
     */
    Optional<AccountRecord> findByUsername(String username);
}