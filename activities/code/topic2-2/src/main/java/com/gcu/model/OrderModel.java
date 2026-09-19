package com.gcu.model;

/**
 * Holds the information displayed for an order.
 */
public class OrderModel {

    private Long id;
    private String orderNo;
    private String productName;
    private float price;
    private int quantity;

    /**
     * Creates an order with all its values.
     *
     * @param id the order identifier
     * @param orderNo the order number
     * @param productName the ordered product
     * @param price the price per item
     * @param quantity the number of items ordered
     */
    public OrderModel(Long id, String orderNo, String productName,
            float price, int quantity) {
        this.id = id;
        this.orderNo = orderNo;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Returns the order identifier.
     *
     * @return the order ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Updates the order identifier.
     *
     * @param id the order ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the order number.
     *
     * @return the order number
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * Updates the order number.
     *
     * @param orderNo the order number
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * Returns the product name.
     *
     * @return the product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Updates the product name.
     *
     * @param productName the product name
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Returns the price per item.
     *
     * @return the item price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Updates the price per item.
     *
     * @param price the item price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Returns the quantity ordered.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Updates the quantity ordered.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}