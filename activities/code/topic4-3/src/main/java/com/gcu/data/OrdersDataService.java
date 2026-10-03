package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gcu.data.entity.OrderEntity;
import com.gcu.data.repository.OrdersRepository;

/**
 * Reads orders through the repository and inserts them using custom SQL.
 */
@Service
public class OrdersDataService
        implements DataAccessInterface<OrderEntity> {

    private final OrdersRepository ordersRepository;
    private final JdbcTemplate jdbcTemplateObject;

    /** Receives the repository and configured database connection source. */
    public OrdersDataService(
            OrdersRepository ordersRepository,
            DataSource dataSource) {
        this.ordersRepository = ordersRepository;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    /** Retrieves orders through the repository's custom findAll query. */
    @Override
    public List<OrderEntity> findAll() {
        List<OrderEntity> orders = new ArrayList<>();

        try {
            orders.addAll(ordersRepository.findAll());
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

    /** Inserts an order using SQL instead of the repository's save method. */
    @Override
    public boolean create(OrderEntity order) {
        String sql = "INSERT INTO ORDERS "
                + "(ORDER_NO, PRODUCT_NAME, PRICE, QUANTITY) "
                + "VALUES (?, ?, ?, ?)";

        try {
            int rows = jdbcTemplateObject.update(
                    sql,
                    order.getOrderNo(),
                    order.getProductName(),
                    order.getPrice(),
                    order.getQuantity());

            return rows == 1;
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