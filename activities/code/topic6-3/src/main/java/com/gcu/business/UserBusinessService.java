package com.gcu.business;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gcu.data.UsersDataService;
import com.gcu.data.entity.UserEntity;

/**
 * Supplies database account details to Spring Security.
 */
@Service
public class UserBusinessService implements UserDetailsService {

    private final UsersDataService service;

    /**
     * Receives the account data service.
     * @param service account lookup service
     */
    public UserBusinessService(UsersDataService service) {
        this.service = service;
    }

    /**
     * Loads the stored hash and authorities for authentication.
     * Spring Security performs the password comparison.
     * @param username submitted login name
     * @return Spring Security account details
     * @throws UsernameNotFoundException when the account does not exist
     */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserEntity account = service.findByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException("Account not found.");
        }

        return User.withUsername(account.getUsername())
                .password(account.getPassword())
                .roles("USER")
                .build();
    }
}