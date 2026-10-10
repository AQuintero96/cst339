package com.gcu.data;

import java.util.List;

/**
 * Defines the data operations supported by an application's data service.
 *
 * @param <T> the entity type
 */
public interface DataAccessInterface<T> {

    /** Returns all records. */
    List<T> findAll();

    /** Finds a record using its MongoDB identifier. */
    T findById(String id);

    /** Creates a record. */
    boolean create(T item);

    /** Updates a record when implemented. */
    boolean update(T item);

    /** Deletes a record when implemented. */
    boolean delete(T item);
}