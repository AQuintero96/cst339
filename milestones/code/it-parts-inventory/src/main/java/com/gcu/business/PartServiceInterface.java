package com.gcu.business;

import com.gcu.model.PartModel;

/**
 * Defines the part creation operation.
 */
public interface PartServiceInterface {

    /**
     * Saves validated part information.
     *
     * @param part the validated part information
     * @return the saved part with its database identifier
     */
    PartModel createPart(PartModel part);
}