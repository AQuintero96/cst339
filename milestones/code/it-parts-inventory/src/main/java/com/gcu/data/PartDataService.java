package com.gcu.data;

import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gcu.model.PartModel;

/**
 * Inserts parts using Spring JDBC and retrieves database-generated IDs.
 */
@Repository
public class PartDataService implements PartDataAccessInterface {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Receives the configured JDBC template.
     *
     * @param jdbcTemplate the database access helper
     */
    public PartDataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Stores a part and retrieves its generated identifier.
     *
     * @param part the validated part information
     * @return the database-generated ID
     */
    @Override
    @Transactional
    public Long create(PartModel part) {
        String sql = "INSERT INTO parts "
                + "(part_name, category, manufacturer, model, quantity, "
                + "unit_cost, storage_location, description) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rows = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, part.getPartName());
            statement.setString(2, part.getCategory());
            statement.setString(3, part.getManufacturer());
            statement.setString(4, part.getModel());
            statement.setInt(5, part.getQuantity());
            statement.setBigDecimal(6, part.getUnitCost());
            statement.setString(7, part.getStorageLocation());
            statement.setString(8, part.getDescription());

            return statement;
        }, keyHolder);

        Number generatedId = keyHolder.getKey();

        // A failure here rolls back the insert rather than reporting success.
        if (rows != 1 || generatedId == null) {
            throw new org.springframework.dao.DataRetrievalFailureException(
                    "The part could not be saved with a generated identifier.");
        }

        return generatedId.longValue();
    }
}