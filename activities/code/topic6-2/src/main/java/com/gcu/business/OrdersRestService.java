package com.gcu.business;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.model.OrderList;
import com.gcu.model.OrderModel;

/**
 * Exposes MongoDB orders as JSON, XML, and individual JSON responses.
 */
@RestController
@RequestMapping("/service")
public class OrdersRestService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(OrdersRestService.class);

    @Autowired
    private OrdersBusinessServiceInterface service;

    /** Returns all orders as JSON. */
    @GetMapping(
            path = "/getjson",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderModel> getOrdersAsJson() {
        return service.getOrders();
    }

    /** Returns all orders inside an XML root element. */
    @GetMapping(
            path = "/getxml",
            produces = MediaType.APPLICATION_XML_VALUE)
    public OrderList getOrdersAsXml() {
        OrderList orderList = new OrderList();
        orderList.setOrders(service.getOrders());
        return orderList;
    }

    /** Returns HTTP 200, 404, or 500 based on the lookup result. */
    @GetMapping(
            path = "/getorder/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOrderById(@PathVariable("id") String id) {
        try {
            OrderModel order = service.getOrderById(id);

            if (order == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap(
                                "error", "Order not found."));
            }

            return ResponseEntity.ok(order);
        } catch (Exception exception) {
            // Reports the failure type without exposing connection details.
            LOGGER.error("Order lookup failed: {}",
                    exception.getClass().getSimpleName());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap(
                            "error", "Unable to retrieve the order."));
        }
    }
}