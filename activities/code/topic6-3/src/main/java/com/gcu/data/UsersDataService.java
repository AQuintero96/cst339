package com.gcu.data;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gcu.data.entity.UserEntity;
import com.gcu.data.repository.UsersRepository;

/**
 * Retrieves accounts for authentication through a MongoDB repository.
 * Optional account-management operations are not implemented.
 */
@Service
public class UsersDataService
        implements UsersDataAccessInterface<UserEntity>,
                   DataAccessInterface<UserEntity> {

    private final UsersRepository usersRepository;

    /**
     * Receives the account repository.
     * @param usersRepository MongoDB account repository
     */
    public UsersDataService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /**
     * Retrieves the account needed by Spring Security.
     * @param username submitted login name
     * @return matching account, or null
     */
    @Override
    public UserEntity findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    /** Account listing is outside the authentication exercise. */
    @Override
    public List<UserEntity> findAll() {
        throw new UnsupportedOperationException(
                "Account listing is not implemented.");
    }

    /** Identifier lookup is outside the authentication exercise. */
    @Override
    public UserEntity findById(String id) {
        throw new UnsupportedOperationException(
                "Account identifier lookup is not implemented.");
    }

    /** Accounts are provisioned through Atlas for this exercise. */
    @Override
    public boolean create(UserEntity user) {
        throw new UnsupportedOperationException(
                "Account creation is not implemented.");
    }

    /** Account updates are outside the authentication exercise. */
    @Override
    public boolean update(UserEntity user) {
        throw new UnsupportedOperationException(
                "Account updates are not implemented.");
    }

    /** Account deletion is outside the authentication exercise. */
    @Override
    public boolean delete(UserEntity user) {
        throw new UnsupportedOperationException(
                "Account deletion is not implemented.");
    }
}