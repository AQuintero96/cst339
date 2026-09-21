package com.gcu.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Holds registration form values and their validation rules.
 * Database persistence will be introduced in a later milestone.
 */
public class UserModel {

    @NotBlank(message = "First name is required.")
    @Size(max = 50, message = "First name cannot exceed 50 characters.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    @Size(max = 50, message = "Last name cannot exceed 50 characters.")
    private String lastName;

    @NotBlank(message = "Email address is required.")
    @Email(message = "Enter a valid email address.")
    @Size(max = 254, message = "Email address cannot exceed 254 characters.")
    private String email;

    @NotBlank(message = "Phone number is required.")
    @Pattern(
        regexp = "^[0-9]{10}$",
        message = "Enter a 10-digit phone number using numbers only."
    )
    private String phoneNumber;

    @NotBlank(message = "Username is required.")
    @Size(
        min = 3,
        max = 32,
        message = "Username must contain between 3 and 32 characters."
    )
    @Pattern(
        regexp = "^[a-zA-Z0-9_]+$",
        message = "Username can contain only letters, numbers, and underscores."
    )
    private String username;

    @NotBlank(message = "Password is required.")
    @Size(
        min = 8,
        max = 64,
        message = "Password must contain between 8 and 64 characters."
    )
    private String password;

    @NotBlank(message = "Please confirm your password.")
    @Size(
        min = 8,
        max = 64,
        message = "Password confirmation must contain between 8 and 64 characters."
    )
    private String confirmPassword;

    /**
     * Creates an empty registration form model.
     */
    public UserModel() {
    }

    /**
     * Returns the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the submitted first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the submitted last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address.
     *
     * @param email the submitted email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number.
     *
     * @param phoneNumber the submitted 10-digit phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the submitted username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password for registration processing.
     *
     * @return the submitted password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the submitted password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the password confirmation.
     *
     * @return the submitted password confirmation
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * Sets the password confirmation.
     *
     * @param confirmPassword the repeated password
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}