package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.gcu.model.OrderModel;

/**
 * Reads and creates MySQL orders using Spring JDBC.
 */
@Service
public class OrdersDataService implements DataAccessInterface<OrderModel> {

    private final JdbcTemplate jdbcTemplateObject;

    /**
     * Builds the JDBC template from Spring's configured data source.
     *
     * @param dataSource the database connection source
     */
    public OrdersDataService(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    /** Reads the database rows and converts them to order models. */
    @Override
    public List<OrderModel> findAll() {
        String sql = "SELECT * FROM ORDERS";
        List<OrderModel> orders = new ArrayList<>();

        try {
            SqlRowSet results = jdbcTemplateObject.queryForRowSet(sql);

            while (results.next()) {
                orders.add(new OrderModel(
                        results.getLong("ID"),
                        results.getString("ORDER_NO"),
                        results.getString("PRODUCT_NAME"),
                        results.getFloat("PRICE"),
                        results.getInt("QUANTITY")));
            }
        } catch (Exception exception) {
            // Reports database failures in the console for this activity.
            exception.printStackTrace();
        }

        return orders;
    }

    /** Remains a placeholder as directed by the Part 1 guide. */
    @Override
    public OrderModel findById(int id) {
        return null;
    }

    /** Inserts an order using bound SQL parameters. */
    @Override
    public boolean create(OrderModel order) {
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
    public boolean update(OrderModel order) {
        return true;
    }

    /** Guide placeholder; does not delete from the database. */
    @Override
    public boolean delete(OrderModel order) {
        return true;
    }
}