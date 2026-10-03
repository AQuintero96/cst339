package com.gcu.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Maps an order to the database without adding persistence
 * annotations to the presentation model.
 */
@Table("ORDERS")
public class OrderEntity {

    @Id
    @Column("ID")
    private Long id;

    @Column("ORDER_NO")
    private String orderNo;

    @Column("PRODUCT_NAME")
    private String productName;

    @Column("PRICE")
    private float price;

    @Column("QUANTITY")
    private int quantity;

    /** Creates an empty entity for mapping. */
    public OrderEntity() {
    }

    /** Creates an entity from all database values. */
    public OrderEntity(Long id, String orderNo, String productName,
                       float price, int quantity) {
        this.id = id;
        this.orderNo = orderNo;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    /** Returns the database identifier. */
    public Long getId() {
        return id;
    }

    /** Sets the database identifier. */
    public void setId(Long id) {
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

    /** Returns the price. */
    public float getPrice() {
        return price;
    }

    /** Sets the price. */
    public void setPrice(float price) {
        this.price = price;
    }

    /** Returns the quantity. */
    public int getQuantity() {
        return quantity;
    }

    /** Sets the quantity. */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}