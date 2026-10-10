package com.gcu.business;

import java.util.List;

import com.gcu.model.OrderModel;

/**
 * Defines order retrieval operations and service lifecycle callbacks.
 */
public interface OrdersBusinessServiceInterface {

    /** Initializes the service. */
    void init();

    /** Performs service cleanup. */
    void destroy();

    /** Identifies the service implementation in the console. */
    void test();

    /** Returns all orders. */
    List<OrderModel> getOrders();

    /** Returns an order by document identifier, or null if absent. */
    OrderModel getOrderById(String id);
}