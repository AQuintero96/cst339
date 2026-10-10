package com.gcu.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Maps an order to a document in the MongoDB orders collection.
 */
@Document(collection = "orders")
public class OrderEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String orderNo;

    @Indexed(unique = true)
    private String productName;

    private float price;
    private int quantity;

    /** Creates an empty entity for document mapping. */
    public OrderEntity() {
    }

    /** Creates an entity with all order values. */
    public OrderEntity(String id, String orderNo, String productName,
                       float price, int quantity) {
        this.id = id;
        this.orderNo = orderNo;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    /** Returns the MongoDB document identifier. */
    public String getId() {
        return id;
    }

    /** Sets the MongoDB document identifier. */
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