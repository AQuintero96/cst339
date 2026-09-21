package com.gcu.business;

import org.springframework.stereotype.Service;

/**
 * Provides registration checks outside the web controller.
 */
@Service
public class RegistrationService {

    /**
     * Checks whether the password and confirmation match.
     *
     * @param password the submitted password
     * @param confirmPassword the repeated password
     * @return true when both values are present and equal
     */
    public boolean passwordsMatch(String password, String confirmPassword) {
        return password != null
                && confirmPassword != null
                && password.equals(confirmPassword);
    }
}