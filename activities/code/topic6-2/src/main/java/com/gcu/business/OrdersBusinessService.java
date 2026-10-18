package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.OrdersDataService;
import com.gcu.data.entity.OrderEntity;
import com.gcu.model.OrderModel;

/**
 * Converts MongoDB entities into models for controllers and views.
 */
public class OrdersBusinessService
        implements OrdersBusinessServiceInterface {

    @Autowired
    private OrdersDataService service;

    /** Reports initialization. */
    @Override
    public void init() {
        System.out.println("OrdersBusinessService: init()");
    }

    /** Reports cleanup. */
    @Override
    public void destroy() {
        System.out.println("OrdersBusinessService: destroy()");
    }

    /** Identifies this implementation. */
    @Override
    public void test() {
        System.out.println("Hello from the OrdersBusinessService");
    }

    /** Converts all stored entities into presentation models. */
    @Override
    public List<OrderModel> getOrders() {
        List<OrderModel> orders = new ArrayList<>();

        for (OrderEntity entity : service.findAll()) {
            orders.add(toModel(entity));
        }

        return orders;
    }

    /** Retrieves one order, preserving null when no document matches. */
    @Override
    public OrderModel getOrderById(String id) {
        OrderEntity entity = service.findById(id);
        return entity == null ? null : toModel(entity);
    }

    /** Keeps persistence-specific entities out of the presentation layer. */
    private OrderModel toModel(OrderEntity entity) {
        return new OrderModel(
                entity.getId(),
                entity.getOrderNo(),
                entity.getProductName(),
                entity.getPrice(),
                entity.getQuantity());
    }
}