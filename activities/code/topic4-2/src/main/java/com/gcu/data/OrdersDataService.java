package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gcu.data.entity.OrderEntity;
import com.gcu.data.repository.OrdersRepository;

/**
 * Accesses orders through a Spring Data JDBC repository.
 */
@Service
public class OrdersDataService
        implements DataAccessInterface<OrderEntity> {

    private final OrdersRepository ordersRepository;

    /** Receives the repository through constructor injection. */
    public OrdersDataService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    /** Converts the repository's iterable result into a list. */
    @Override
    public List<OrderEntity> findAll() {
        List<OrderEntity> orders = new ArrayList<>();

        try {
            Iterable<OrderEntity> orderIterable =
                    ordersRepository.findAll();
            orderIterable.forEach(orders::add);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return orders;
    }

    /** Remains a placeholder as directed by the guide. */
    @Override
    public OrderEntity findById(int id) {
        return null;
    }

    /** Saves an entity and reports whether the operation succeeded. */
    @Override
    public boolean create(OrderEntity order) {
        try {
            ordersRepository.save(order);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    /** Guide placeholder; does not update the database. */
    @Override
    public boolean update(OrderEntity order) {
        return true;
    }

    /** Guide placeholder; does not delete from the database. */
    @Override
    public boolean delete(OrderEntity order) {
        return true;
    }
}