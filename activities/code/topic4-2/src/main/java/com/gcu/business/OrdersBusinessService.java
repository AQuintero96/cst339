package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.OrdersDataService;
import com.gcu.data.entity.OrderEntity;
import com.gcu.model.OrderModel;

/**
 * Converts database entities into models used by the presentation layer.
 */
public class OrdersBusinessService
        implements OrdersBusinessServiceInterface {

    @Autowired
    private OrdersDataService service;

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

    /** Identifies this implementation. */
    @Override
    public void test() {
        System.out.println("Hello from the OrdersBusinessService");
    }

    /** Keeps persistence-specific entities out of the controller and views. */
    @Override
    public List<OrderModel> getOrders() {
        List<OrderEntity> ordersEntity = service.findAll();
        List<OrderModel> ordersDomain = new ArrayList<>();

        for (OrderEntity entity : ordersEntity) {
            ordersDomain.add(new OrderModel(
                    entity.getId(),
                    entity.getOrderNo(),
                    entity.getProductName(),
                    entity.getPrice(),
                    entity.getQuantity()));
        }

        return ordersDomain;
    }
}