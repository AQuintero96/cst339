package com.gcu.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gcu.data.entity.UserEntity;

/**
 * Provides MongoDB account persistence and username lookup.
 */
public interface UsersRepository
        extends MongoRepository<UserEntity, String> {

    /**
     * Retrieves an account by its exact username.
     * @param username login name
     * @return matching account, or null when absent
     */
    UserEntity findByUsername(String username);
}