package com.gcu.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Holds the submitted login credentials and their validation rules.
 */
public class LoginModel {

    @NotBlank(message = "Username is required.")
    @Size(max = 32, message = "Username cannot exceed 32 characters.")
    private String username;

    @NotBlank(message = "Password is required.")
    @Size(max = 64, message = "Password cannot exceed 64 characters.")
    private String password;

    /**
     * Creates an empty login form.
     */
    public LoginModel() {
    }

    /**
     * Returns the submitted username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the submitted username.
     *
     * @param username the username entered on the form
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
     * Sets the submitted password.
     *
     * @param password the password entered on the form
     */
    public void setPassword(String password) {
        this.password = password;
    }
}