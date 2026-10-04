package com.gcu.data;

import com.gcu.model.PartModel;

/**
 * Defines persistence operations for part creation.
 */
public interface PartDataAccessInterface {

    /**
     * Inserts a validated part and returns its database identifier.
     *
     * @param part the part to store
     * @return the generated part ID
     */
    Long create(PartModel part);
}