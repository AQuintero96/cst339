package com.gcu.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gcu.data.entity.OrderEntity;

/**
 * Converts a JDBC result row into an order entity.
 */
public class OrderRowMapper implements RowMapper<OrderEntity> {

    /** Reads the current row's columns. */
    @Override
    public OrderEntity mapRow(ResultSet results, int rowNumber)
            throws SQLException {
        return new OrderEntity(
                results.getLong("ID"),
                results.getString("ORDER_NO"),
                results.getString("PRODUCT_NAME"),
                results.getFloat("PRICE"),
                results.getInt("QUANTITY"));
    }
}