package com.gcu.business;

import com.gcu.model.UserModel;

/**
 * Defines account registration operations.
 * Authentication is handled separately by Spring Security.
 */
public interface AccountServiceInterface {

    /**
     * Registers a validated user when the username is available.
     *
     * @param user validated registration details
     * @return true when created, or false for a duplicate username
     */
    boolean register(UserModel user);
}