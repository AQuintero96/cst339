package com.gcu.business;

import com.gcu.model.PartModel;

/**
 * Defines the part creation operation.
 */
public interface PartServiceInterface {

    /**
     * Processes a validated part submission.
     *
     * @param part the validated part information
     * @return the created part with a temporary identifier
     */
    PartModel createPart(PartModel part);
}