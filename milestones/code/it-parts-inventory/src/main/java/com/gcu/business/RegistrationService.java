package com.gcu.business;

import org.springframework.stereotype.Service;

/**
 * Implements the registration password confirmation check.
 */
@Service
public class RegistrationService
        implements RegistrationServiceInterface {

    /**
     * Checks whether the password and confirmation match.
     *
     * @param password the submitted password
     * @param confirmPassword the repeated password
     * @return true when both values are present and equal
     */
    @Override
    public boolean passwordsMatch(String password, String confirmPassword) {
        return password != null
                && confirmPassword != null
                && password.equals(confirmPassword);
    }
}