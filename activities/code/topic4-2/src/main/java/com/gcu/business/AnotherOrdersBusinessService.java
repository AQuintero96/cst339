package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import com.gcu.model.OrderModel;

/**
 * Provides an alternative orders service with lifecycle callbacks.
 */
public class AnotherOrdersBusinessService
        implements OrdersBusinessServiceInterface {

    /**
     * Reports when Spring initializes this service.
     */
    @Override
    public void init() {
        System.out.println("AnotherOrdersBusinessService: init()");
    }

    /**
     * Reports when Spring invokes this service's cleanup callback.
     */
    @Override
    public void destroy() {
        System.out.println("AnotherOrdersBusinessService: destroy()");
    }

    /**
     * Identifies this implementation in the console.
     */
    @Override
    public void test() {
        System.out.println("Hello from the AnotherOrdersBusinessService");
    }

    /**
     * Creates the alternative service's sample order list.
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