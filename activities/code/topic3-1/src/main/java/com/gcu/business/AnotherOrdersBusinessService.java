package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import com.gcu.model.OrderModel;

/**
 * Provides an alternative implementation of the orders service.
 */
public class AnotherOrdersBusinessService
        implements OrdersBusinessServiceInterface {

    /**
     * Identifies the alternative implementation in the console.
     */
    @Override
    public void test() {
        System.out.println("Hello from the AnotherOrdersBusinessService");
    }

    /**
     * Creates sample orders using the same interface contract.
     *
     * @return four sample orders
     */
    @Override
    public List<OrderModel> getOrders() {
        List<OrderModel> orders = new ArrayList<>();

        orders.add(new OrderModel(1L, "1001", "Notebook", 4.99f, 3));
        orders.add(new OrderModel(2L, "1002", "Pen Set", 7.50f, 2));
        orders.add(new OrderModel(3L, "1003", "Desk Organizer", 15.99f, 1));
        orders.add(new OrderModel(4L, "1004", "Folder", 2.25f, 5));

        return orders;
    }
}