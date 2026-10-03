package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.model.OrderList;
import com.gcu.model.OrderModel;

/**
 * Exposes the sample orders as JSON and XML responses.
 */
@RestController
@RequestMapping("/service")
public class OrdersRestService {

    // Uses the orders bean already configured in SpringConfig.
    @Autowired
    private OrdersBusinessServiceInterface service;

    /**
     * Returns the sample orders as a JSON array.
     *
     * @return the orders supplied by the business service
     */
    @GetMapping(
        path = "/getjson",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<OrderModel> getOrdersAsJson() {
        return service.getOrders();
    }

    /**
     * Returns the sample orders inside an XML root element.
     *
     * @return the wrapped order list
     */
    @GetMapping(
        path = "/getxml",
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public OrderList getOrdersAsXml() {
        OrderList orderList = new OrderList();
        orderList.setOrders(service.getOrders());
        return orderList;
    }
}