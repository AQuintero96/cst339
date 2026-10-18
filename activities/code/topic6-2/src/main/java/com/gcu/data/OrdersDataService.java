package com.gcu.data;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gcu.data.entity.OrderEntity;
import com.gcu.data.repository.OrdersRepository;

/**
 * Accesses order documents through a Spring Data MongoDB repository.
 */
@Service
public class OrdersDataService
        implements DataAccessInterface<OrderEntity> {

    private final OrdersRepository ordersRepository;

    /** Receives the repository through constructor injection. */
    public OrdersDataService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    /** Returns all saved orders. */
    @Override
    public List<OrderEntity> findAll() {
        return ordersRepository.findAll();
    }

    /** Uses the repository's derived query to locate an order. */
    @Override
    public OrderEntity findById(String id) {
        return ordersRepository.getOrderById(id);
    }

    /** Saves an order; database failures propagate to the caller. */
    @Override
    public boolean create(OrderEntity order) {
        ordersRepository.save(order);
        return true;
    }

    /** Updating orders is outside this activity's implementation. */
    @Override
    public boolean update(OrderEntity order) {
        throw new UnsupportedOperationException(
                "Updating orders is not implemented.");
    }

    /** Deleting orders is outside this activity's implementation. */
    @Override
    public boolean delete(OrderEntity order) {
        throw new UnsupportedOperationException(
                "Deleting orders is not implemented.");
    }
}