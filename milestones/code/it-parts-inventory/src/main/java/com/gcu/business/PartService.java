package com.gcu.business;

import org.springframework.stereotype.Service;

import com.gcu.data.PartDataAccessInterface;
import com.gcu.model.PartModel;

/**
 * Prepares validated part information and requests database persistence.
 */
@Service
public class PartService implements PartServiceInterface {

    private final PartDataAccessInterface partDataService;

    /**
     * Receives the part DAO through constructor injection.
     *
     * @param partDataService the part persistence service
     */
    public PartService(PartDataAccessInterface partDataService) {
        this.partDataService = partDataService;
    }

    /**
     * Normalizes submitted text and saves the part.
     *
     * @param part the validated submission
     * @return the saved part with its database identifier
     */
    @Override
    public PartModel createPart(PartModel part) {
        PartModel createdPart = new PartModel();

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

        // MySQL assigns the identifier after the insert.
        createdPart.setPartId(partDataService.create(createdPart));
        return createdPart;
    }
}