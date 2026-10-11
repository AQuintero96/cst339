package com.gcu.business;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.stereotype.Service;

import com.gcu.data.PartDataAccessInterface;
import com.gcu.model.PartModel;

/**
 * Validates and normalizes part data before requesting persistence.
 */
@Service
public class PartService implements PartServiceInterface {

    private final PartDataAccessInterface partDataService;
    private final Validator validator;

    /**
     * Receives persistence and validation services through injection.
     * @param partDataService part DAO
     * @param validator Bean Validation service
     */
    public PartService(
            PartDataAccessInterface partDataService,
            Validator validator) {
        this.partDataService = partDataService;
        this.validator = validator;
    }

    /**
     * Saves a validated part and returns its generated identifier.
     * @param part submitted values
     * @return saved part
     */
    @Override
    public PartModel createPart(PartModel part) {
        PartModel saved = preparePart(part);
        saved.setPartId(partDataService.create(saved));
        return saved;
    }

    /**
     * Retrieves all parts from the database.
     * @return stored inventory
     */
    @Override
    public List<PartModel> getParts() {
        return partDataService.findAll();
    }

    /**
     * Retrieves a part when the identifier is valid.
     * @param id requested identifier
     * @return matching part, or empty
     */
    @Override
    public Optional<PartModel> getPartById(Long id) {
        if (id == null || id <= 0) {
            return Optional.empty();
        }

        return partDataService.findById(id);
    }

    /**
     * Updates a part using the route identifier rather than form data.
     * @param id requested identifier
     * @param part submitted values
     * @return whether an existing row was updated
     */
    @Override
    public boolean updatePart(Long id, PartModel part) {
        if (id == null || id <= 0) {
            return false;
        }

        PartModel updated = preparePart(part);
        updated.setPartId(id);
        return partDataService.update(updated);
    }

    /**
     * Deletes the specified part.
     * @param id requested identifier
     * @return whether an existing row was deleted
     */
    @Override
    public boolean deletePart(Long id) {
        if (id == null || id <= 0) {
            return false;
        }

        return partDataService.delete(id);
    }

    /**
     * Validates input and copies editable values into a separate model.
     * @param submitted original form values
     * @return normalized values
     */
    private PartModel preparePart(PartModel submitted) {
        if (submitted == null) {
            throw new IllegalArgumentException("Part information is required.");
        }

        // Protects persistence even when called outside an MVC form.
        Set<ConstraintViolation<PartModel>> violations =
                validator.validate(submitted);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        PartModel prepared = new PartModel();
        prepared.setPartName(submitted.getPartName().trim());
        prepared.setCategory(submitted.getCategory());
        prepared.setManufacturer(submitted.getManufacturer().trim());
        prepared.setModel(submitted.getModel().trim());
        prepared.setQuantity(submitted.getQuantity());
        prepared.setUnitCost(submitted.getUnitCost());
        prepared.setStorageLocation(submitted.getStorageLocation().trim());

        String description = submitted.getDescription();
        prepared.setDescription(
                description == null ? "" : description.trim());

        return prepared;
    }
}