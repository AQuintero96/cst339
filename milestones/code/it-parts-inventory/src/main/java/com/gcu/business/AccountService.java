package com.gcu.business;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gcu.data.AccountDataAccessInterface;
import com.gcu.model.UserModel;

/**
 * Registers database accounts using the shared password encoder.
 */
@Service
public class AccountService implements AccountServiceInterface {

    private final AccountDataAccessInterface accountDataService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Receives account persistence and password encoding dependencies.
     *
     * @param accountDataService account DAO
     * @param passwordEncoder encoder shared with authentication
     */
    public AccountService(
            AccountDataAccessInterface accountDataService,
            PasswordEncoder passwordEncoder) {
        this.accountDataService = accountDataService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Stores a validated registration with an encoded password.
     *
     * @param user validated registration information
     * @return true when created, or false for a duplicate username
     */
    @Override
    public boolean register(UserModel user) {
        String encodedPassword =
                passwordEncoder.encode(user.getPassword());

        try {
            accountDataService.create(user, encodedPassword);
            return true;
        } catch (DuplicateKeyException exception) {
            // The database constraint also prevents concurrent duplicates.
            return false;
        }
    }
}