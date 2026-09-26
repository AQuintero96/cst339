package com.gcu.business;

import java.util.List;

import com.gcu.model.OrderModel;

/**
 * Defines the operations provided by an orders business service.
 */
public interface OrdersBusinessServiceInterface {

    /**
     * Prints a message identifying the service implementation.
     */
    void test();

    /**
     * Returns the orders displayed by the application.
     *
     * @return the list of orders
     */
    List<OrderModel> getOrders();
}