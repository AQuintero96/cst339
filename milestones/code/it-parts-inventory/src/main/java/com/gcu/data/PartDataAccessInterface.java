package com.gcu.data;

import java.util.List;
import java.util.Optional;

import com.gcu.model.PartModel;

/**
 * Defines database operations for the shared parts inventory.
 */
public interface PartDataAccessInterface {

    /**
     * Inserts a part.
     * @param part validated part information
     * @return generated identifier
     */
    Long create(PartModel part);

    /**
     * Reads the inventory.
     * @return parts ordered by identifier
     */
    List<PartModel> findAll();

    /**
     * Looks up one part.
     * @param id part identifier
     * @return matching part, or empty when absent
     */
    Optional<PartModel> findById(Long id);

    /**
     * Updates an existing part.
     * @param part validated values including the identifier
     * @return true when a matching row was updated
     */
    boolean update(PartModel part);

    /**
     * Deletes an existing part.
     * @param id part identifier
     * @return true when a matching row was deleted
     */
    boolean delete(Long id);
}