package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Stores the login form values and defines their validation rules.
 */
public class LoginModel {

    @NotNull(message = "User name is a required field")
    @Size(min = 1, max = 32,
          message = "User name must be between 1 and 32 characters")
    private String username;

    @NotNull(message = "Password is a required field")
    @Size(min = 1, max = 32,
          message = "Password must be between 1 and 32 characters")
    private String password;

    /**
     * Creates an empty model for the login form.
     */
    public LoginModel() {
    }

    /**
     * Returns the submitted user name.
     *
     * @return the user name
     */
    public String getUsername() {
        return username;
    }

    /**
     * Stores the submitted user name.
     *
     * @param username the user name from the form
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the submitted password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Stores the submitted password.
     *
     * @param password the password from the form
     */
    public void setPassword(String password) {
        this.password = password;
    }
}