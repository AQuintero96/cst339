package com.gcu.model;

import java.io.Serializable;

/**
 * Holds the user information needed for the simulated login session.
 */
public class SessionUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String username;
    private final String firstName;

    /**
     * Creates the user information stored after a successful login.
     *
     * @param username the registered username
     * @param firstName the user's first name
     */
    public SessionUser(String username, String firstName) {
        this.username = username;
        this.firstName = firstName;
    }

    /**
     * Returns the registered username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the first name for the welcome message.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }
}