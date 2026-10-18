package com.gcu.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gcu.data.AccountDataAccessInterface;
import com.gcu.model.AccountRecord;

/**
 * Supplies database accounts to Spring Security during authentication.
 */
@Service
public class InventoryUserDetailsService implements UserDetailsService {

    private final AccountDataAccessInterface accountDataService;

    /**
     * Receives the existing account persistence service.
     *
     * @param accountDataService account DAO
     */
    public InventoryUserDetailsService(
            AccountDataAccessInterface accountDataService) {
        this.accountDataService = accountDataService;
    }

    /**
     * Retrieves the account associated with a submitted username.
     * Password verification is performed by Spring Security's provider.
     *
     * @param username submitted username
     * @return security user details
     * @throws UsernameNotFoundException when the account does not exist
     */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        if (username == null || username.trim().isEmpty()) {
            throw new UsernameNotFoundException("Account not found.");
        }

        // The DAO applies the same username normalization as registration.
        AccountRecord account = accountDataService.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Account not found."));

        return new InventoryUserDetails(account);
    }
}