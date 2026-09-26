package com.gcu.business;

/**
 * Defines the password confirmation check used during registration.
 */
public interface RegistrationServiceInterface {

    /**
     * Checks whether the password and confirmation match.
     *
     * @param password the submitted password
     * @param confirmPassword the repeated password
     * @return true when both values are present and equal
     */
    boolean passwordsMatch(String password, String confirmPassword);
}