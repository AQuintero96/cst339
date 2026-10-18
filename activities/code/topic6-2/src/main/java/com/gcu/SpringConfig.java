package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gcu.business.OrdersBusinessService;
import com.gcu.business.OrdersBusinessServiceInterface;

/**
 * Configures the orders service using the default singleton scope.
 */
@Configuration
public class SpringConfig {

    /**
     * Creates one shared orders service instance for this application context.
     *
     * @return the shared orders service
     */
    @Bean(
        name = "ordersBusinessService",
        initMethod = "init",
        destroyMethod = "destroy"
    )
    public OrdersBusinessServiceInterface getOrdersBusiness() {
        return new OrdersBusinessService();
    }
}