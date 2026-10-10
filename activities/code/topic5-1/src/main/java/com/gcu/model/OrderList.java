package com.gcu.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Wraps the order list in a root element for the XML response.
 */
@XmlRootElement(name = "orders")
public class OrderList {

    private List<OrderModel> orders = new ArrayList<>();

    /**
     * Creates an empty order collection for XML binding.
     */
    public OrderList() {
    }

    /**
     * Returns the orders, with each item represented by an order element.
     *
     * @return the order list
     */
    @XmlElement(name = "order")
    public List<OrderModel> getOrders() {
        return orders;
    }

    /**
     * Sets the orders included in the response.
     *
     * @param orders the orders supplied by the business service
     */
    public void setOrders(List<OrderModel> orders) {
        this.orders = orders;
    }
}