package com.gcu.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gcu.data.entity.OrderEntity;

/**
 * Provides MongoDB persistence and a derived identifier query.
 */
public interface OrdersRepository
        extends MongoRepository<OrderEntity, String> {

    /** Returns the matching order, or null when no document matches. */
    OrderEntity getOrderById(String id);
}