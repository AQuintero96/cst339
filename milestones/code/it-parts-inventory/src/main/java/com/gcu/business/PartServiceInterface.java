package com.gcu.business;

import java.util.List;
import java.util.Optional;

import com.gcu.model.PartModel;

/**
 * Defines business operations for the shared parts inventory.
 */
public interface PartServiceInterface {

    /**
     * Validates and saves a new part.
     * @param part submitted values
     * @return saved part with its generated identifier
     */
    PartModel createPart(PartModel part);

    /**
     * Retrieves the inventory.
     * @return all stored parts
     */
    List<PartModel> getParts();

    /**
     * Retrieves one part.
     * @param id part identifier
     * @return matching part, or empty
     */
    Optional<PartModel> getPartById(Long id);

    /**
     * Validates and updates an existing part.
     * @param id identifier from the requested route
     * @param part submitted values
     * @return whether the part still existed and was updated
     */
    boolean updatePart(Long id, PartModel part);

    /**
     * Deletes an existing part.
     * @param id part identifier
     * @return whether the part existed and was deleted
     */
    boolean deletePart(Long id);
}