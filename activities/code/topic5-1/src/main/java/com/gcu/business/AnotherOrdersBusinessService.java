package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import com.gcu.model.OrderModel;

/**
 * Retains the alternative sample service from the earlier activity.
 * SpringConfig selects OrdersBusinessService for MongoDB access.
 */
public class AnotherOrdersBusinessService
        implements OrdersBusinessServiceInterface {

    /** Reports initialization. */
    @Override
    public void init() {
        System.out.println("AnotherOrdersBusinessService: init()");
    }

    /** Reports cleanup. */
    @Override
    public void destroy() {
        System.out.println("AnotherOrdersBusinessService: destroy()");
    }

    /** Identifies this implementation. */
    @Override
    public void test() {
        System.out.println("Hello from the AnotherOrdersBusinessService");
    }

    /** Returns the alternative service's sample orders. */
    @Override
    public List<OrderModel> getOrders() {
        List<OrderModel> orders = new ArrayList<>();

        orders.add(new OrderModel("1", "1001", "Notebook", 4.99f, 3));
        orders.add(new OrderModel("2", "1002", "Pen Set", 7.50f, 2));
        orders.add(new OrderModel("3", "1003", "Desk Organizer", 15.99f, 1));
        orders.add(new OrderModel("4", "1004", "Folder", 2.25f, 5));

        return orders;
    }

    /** Finds an order within this alternative sample list. */
    @Override
    public OrderModel getOrderById(String id) {
        return getOrders().stream()
                .filter(order -> order.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}