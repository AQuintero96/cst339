package com.gcu.business;

import java.util.Optional;

import com.gcu.model.SessionUser;
import com.gcu.model.UserModel;

/**
 * Defines account registration and authentication operations.
 */
public interface AccountServiceInterface {

    /**
     * Registers a validated user when the username is available.
     *
     * @param user the validated registration details
     * @return true when registration succeeds, or false for a duplicate username
     */
    boolean register(UserModel user);

    /**
     * Checks the submitted login credentials.
     *
     * @param username the submitted username
     * @param password the submitted password
     * @return the session user when authentication succeeds
     */
    Optional<SessionUser> authenticate(String username, String password);
}