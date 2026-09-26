package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gcu.business.OrdersBusinessService;
import com.gcu.business.OrdersBusinessServiceInterface;

/**
 * Configures the orders business service managed by Spring.
 */
@Configuration
public class SpringConfig {

    /**
     * Creates the orders service injected into the login controller.
     *
     * @return the original orders service implementation
     */
    @Bean(name = "ordersBusinessService")
    public OrdersBusinessServiceInterface getOrdersBusiness() {
        return new OrdersBusinessService();
    }
}