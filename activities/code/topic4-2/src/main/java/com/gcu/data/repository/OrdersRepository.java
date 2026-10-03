package com.gcu.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.gcu.data.entity.OrderEntity;

/**
 * Provides Spring Data JDBC's standard order persistence operations.
 */
public interface OrdersRepository
        extends CrudRepository<OrderEntity, Long> {
}