package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.OrderModel;

/**
 * Supplies database orders through an injected data service.
 */
public class OrdersBusinessService
        implements OrdersBusinessServiceInterface {

    @Autowired
    private DataAccessInterface<OrderModel> service;

    /** Reports service initialization. */
    @Override
    public void init() {
        System.out.println("OrdersBusinessService: init()");
    }

    /** Reports service cleanup. */
    @Override
    public void destroy() {
        System.out.println("OrdersBusinessService: destroy()");
    }

    /** Identifies the service in the console. */
    @Override
    public void test() {
        System.out.println("Hello from the OrdersBusinessService");
    }

    /** Returns orders read by the data service. */
    @Override
    public List<OrderModel> getOrders() {
        return service.findAll();
    }
}