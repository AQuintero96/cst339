package com.gcu.business;

import java.util.List;

import com.gcu.model.OrderModel;

/**
 * Defines the orders service operations and lifecycle callbacks.
 */
public interface OrdersBusinessServiceInterface {

    /**
     * Initializes the service after Spring creates it.
     */
    void init();

    /**
     * Performs cleanup when Spring destroys a managed instance.
     */
    void destroy();

    /**
     * Prints a message identifying the implementation.
     */
    void test();

    /**
     * Returns the sample orders.
     *
     * @return the order list
     */
    List<OrderModel> getOrders();
}