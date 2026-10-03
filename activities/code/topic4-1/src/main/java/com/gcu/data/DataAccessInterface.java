package com.gcu.data;

import java.util.List;

/**
 * Defines the data operations shared by the activity's data services.
 *
 * @param <T> the object type handled by the service
 */
public interface DataAccessInterface<T> {

    /** Returns all records. */
    List<T> findAll();

    /** Finds a record by its identifier. */
    T findById(int id);

    /** Creates a record. */
    boolean create(T item);

    /** Updates a record. */
    boolean update(T item);

    /** Deletes a record. */
    boolean delete(T item);
}