package com.gcu.model;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Holds part information submitted through the creation form.
 */
public class PartModel {

    private Long partId;

    @NotBlank(message = "Part name is required.")
    @Size(max = 100, message = "Part name cannot exceed 100 characters.")
    private String partName;

    @NotBlank(message = "Category is required.")
    @Pattern(
        regexp = "^(RAM|SSD|Hard Drive|Processor|Motherboard|Power Supply|Graphics Card|Cooling)$",
        message = "Select a supported component category."
    )
    private String category;

    @NotBlank(message = "Manufacturer is required.")
    @Size(max = 60, message = "Manufacturer cannot exceed 60 characters.")
    private String manufacturer;

    @NotBlank(message = "Model is required.")
    @Size(max = 100, message = "Model cannot exceed 100 characters.")
    private String model;

    @NotNull(message = "Quantity is required.")
    @Min(value = 0, message = "Quantity cannot be negative.")
    private Integer quantity;

    @NotNull(message = "Unit cost is required.")
    @DecimalMin(value = "0.00", message = "Unit cost cannot be negative.")
    @Digits(
        integer = 10,
        fraction = 2,
        message = "Unit cost allows up to 10 whole-number digits and 2 decimal places."
    )
    private BigDecimal unitCost;

    @NotBlank(message = "Storage location is required.")
    @Size(
        max = 100,
        message = "Storage location cannot exceed 100 characters."
    )
    private String storageLocation;

    @Size(max = 1000, message = "Description cannot exceed 1,000 characters.")
    private String description;

    /**
     * Creates an empty part for form binding.
     */
    public PartModel() {
    }

    /**
     * Returns the system-assigned identifier.
     *
     * @return the part ID
     */
    public Long getPartId() {
        return partId;
    }

    /**
     * Sets the system-assigned identifier.
     *
     * @param partId the part ID
     */
    public void setPartId(Long partId) {
        this.partId = partId;
    }

    /**
     * Returns the part name.
     *
     * @return the part name
     */
    public String getPartName() {
        return partName;
    }

    /**
     * Sets the part name.
     *
     * @param partName the submitted part name
     */
    public void setPartName(String partName) {
        this.partName = partName;
    }

    /**
     * Returns the component category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the component category.
     *
     * @param category the selected category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the manufacturer.
     *
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets the manufacturer.
     *
     * @param manufacturer the submitted manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Returns the component model.
     *
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the component model.
     *
     * @param model the submitted model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Returns the available quantity.
     *
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the available quantity.
     *
     * @param quantity the submitted quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the unit cost in USD.
     *
     * @return the unit cost
     */
    public BigDecimal getUnitCost() {
        return unitCost;
    }

    /**
     * Sets the unit cost in USD.
     *
     * @param unitCost the submitted unit cost
     */
    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    /**
     * Returns the storage location.
     *
     * @return the storage location
     */
    public String getStorageLocation() {
        return storageLocation;
    }

    /**
     * Sets the storage location.
     *
     * @param storageLocation the submitted location
     */
    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    /**
     * Returns the optional description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the optional description.
     *
     * @param description the submitted notes
     */
    public void setDescription(String description) {
        this.description = description;
    }
}