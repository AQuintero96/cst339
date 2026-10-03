package com.gcu.data.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcu.data.entity.OrderEntity;

/**
 * Retrieves orders using an explicit SQL query.
 */
public interface OrdersRepository
        extends CrudRepository<OrderEntity, Long> {

    /** Replaces the generated findAll query with custom SQL. */
    @Override
    @Query("SELECT * FROM ORDERS")
    List<OrderEntity> findAll();
}