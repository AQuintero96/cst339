package com.gcu;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.gcu.data.OrdersDataService;
import com.gcu.data.entity.OrderEntity;

/**
 * Verifies MongoDB saving and reading when the save-test profile is enabled.
 * Leaves the saved document available for screenshot evidence.
 */
@Component
@Profile("save-test")
public class OrderSaveVerification implements CommandLineRunner {

    private final OrdersDataService service;

    /** Receives the application's data service. */
    public OrderSaveVerification(OrdersDataService service) {
        this.service = service;
    }

    /** Saves a test order once and verifies its stored values. */
    @Override
    public void run(String... args) {
        String orderNumber = "1006";

        // Avoids inserting the same test order during subsequent restarts.
        boolean alreadyExists = service.findAll().stream()
                .anyMatch(order -> orderNumber.equals(order.getOrderNo()));

        if (!alreadyExists) {
            OrderEntity order = new OrderEntity(
                    null, orderNumber, "Java Save Test", 12.50f, 2);

            if (!service.create(order)) {
                throw new IllegalStateException("MongoDB save failed.");
            }
        }

        // Reads the collection again to verify database persistence.
        List<OrderEntity> storedOrders = service.findAll();

        OrderEntity saved = storedOrders.stream()
                .filter(order -> orderNumber.equals(order.getOrderNo()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "The verification order was not found."));

        if (saved.getId() == null
                || !"Java Save Test".equals(saved.getProductName())
                || Float.compare(saved.getPrice(), 12.50f) != 0
                || saved.getQuantity() != 2) {
            throw new IllegalStateException(
                    "The saved order did not match the expected values.");
        }

        System.out.println(
                "MongoDB save/read verification PASSED"
                + " | orderNo=" + saved.getOrderNo()
                + " | id=" + saved.getId()
                + " | totalOrders=" + storedOrders.size());
    }
}