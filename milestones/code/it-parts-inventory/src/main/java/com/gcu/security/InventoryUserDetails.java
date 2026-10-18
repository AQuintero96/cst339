package com.gcu.security;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.gcu.model.AccountRecord;

/**
 * Represents the authenticated inventory user.
 * Extends Spring Security's user implementation to retain the display name.
 */
public class InventoryUserDetails extends User {

    private static final long serialVersionUID = 1L;

    private final Long userId;
    private final String firstName;

    /**
     * Builds security user details from a stored account.
     *
     * @param account account retrieved from MySQL
     */
    public InventoryUserDetails(AccountRecord account) {
        super(
                account.getUsername(),
                account.getPasswordHash(),
                Collections.singletonList(
                        new SimpleGrantedAuthority("ROLE_USER")));

        this.userId = account.getUserId();
        this.firstName = account.getFirstName();
    }

    /**
     * Returns the database account identifier.
     *
     * @return account identifier
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Returns the first name used by the navigation and inventory greeting.
     *
     * @return user's first name
     */
    public String getFirstName() {
        return firstName;
    }
}