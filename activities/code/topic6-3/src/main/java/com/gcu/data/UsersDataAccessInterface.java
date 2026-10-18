package com.gcu.data;

/**
 * Defines account lookup for authentication.
 * @param <T> account entity type
 */
public interface UsersDataAccessInterface<T> {

    /**
     * Finds an account by username.
     * @param username login name
     * @return matching account, or null when absent
     */
    T findByUsername(String username);
}