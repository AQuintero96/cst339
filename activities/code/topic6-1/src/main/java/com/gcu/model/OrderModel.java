package com.gcu.model;

/**
 * Holds order information for HTML, JSON, and XML responses.
 */
public class OrderModel {

    private String id;
    private String orderNo;
    private String productName;
    private float price;
    private int quantity;

    /** Creates an empty model for data binding. */
    public OrderModel() {
    }

    /** Creates a model with all order values. */
    public OrderModel(String id, String orderNo, String productName,
                      float price, int quantity) {
        this.id = id;
        this.orderNo = orderNo;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    /** Returns the document identifier. */
    public String getId() {
        return id;
    }

    /** Sets the document identifier. */
    public void setId(String id) {
        this.id = id;
    }

    /** Returns the order number. */
    public String getOrderNo() {
        return orderNo;
    }

    /** Sets the order number. */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /** Returns the product name. */
    public String getProductName() {
        return productName;
    }

    /** Sets the product name. */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /** Returns the unit price. */
    public float getPrice() {
        return price;
    }

    /** Sets the unit price. */
    public void setPrice(float price) {
        this.price = price;
    }

    /** Returns the quantity ordered. */
    public int getQuantity() {
        return quantity;
    }

    /** Sets the quantity ordered. */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}