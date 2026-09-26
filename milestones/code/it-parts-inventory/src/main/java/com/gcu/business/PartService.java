package com.gcu.business;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.gcu.model.PartModel;

/**
 * Processes part creation without database persistence.
 */
@Service
public class PartService implements PartServiceInterface {

    private final AtomicLong nextId = new AtomicLong(1);

    /**
     * Copies validated values and assigns a temporary identifier.
     *
     * @param part the validated submission
     * @return the part used for the creation confirmation
     */
    @Override
    public PartModel createPart(PartModel part) {
        PartModel createdPart = new PartModel();

        // IDs are temporary and restart at one when the application restarts.
        createdPart.setPartId(nextId.getAndIncrement());
        createdPart.setPartName(part.getPartName().trim());
        createdPart.setCategory(part.getCategory());
        createdPart.setManufacturer(part.getManufacturer().trim());
        createdPart.setModel(part.getModel().trim());
        createdPart.setQuantity(part.getQuantity());
        createdPart.setUnitCost(part.getUnitCost());
        createdPart.setStorageLocation(part.getStorageLocation().trim());

        String description = part.getDescription();
        createdPart.setDescription(
                description == null ? "" : description.trim());

        return createdPart;
    }
}