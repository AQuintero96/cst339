package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.OrdersBusinessServiceInterface;

/**
 * Displays database orders after Spring Security authenticates the user.
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersBusinessServiceInterface service;

    /**
     * Receives the configured orders business service.
     * @param service order retrieval service
     */
    public OrdersController(OrdersBusinessServiceInterface service) {
        this.service = service;
    }

    /**
     * Retrieves orders for the protected page.
     * @param model view attributes
     * @return orders template
     */
    @GetMapping("/display")
    public String displayOrders(Model model) {
        model.addAttribute("title", "My Orders");
        model.addAttribute("orders", service.getOrders());
        return "orders";
    }
}