package com.gcu.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gcu.model.PartModel;

/**
 * Implements part persistence using Spring JDBC and parameterized SQL.
 */
@Repository
public class PartDataService implements PartDataAccessInterface {

    private static final String SELECT_COLUMNS =
            "SELECT part_id, part_name, category, manufacturer, model, "
            + "quantity, unit_cost, storage_location, description FROM parts";

    private final JdbcTemplate jdbcTemplate;

    /**
     * Receives the configured JDBC helper.
     * @param jdbcTemplate database access helper
     */
    public PartDataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Inserts a part and retrieves its generated identifier.
     * @param part validated part values
     * @return generated identifier
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

        // Throwing a runtime exception rolls back an incomplete operation.
        if (rows != 1 || generatedId == null) {
            throw new DataRetrievalFailureException(
                    "The part could not be saved with a generated identifier.");
        }

        return generatedId.longValue();
    }

    /**
     * Reads all inventory rows in a predictable order.
     * @return stored parts
     */
    @Override
    public List<PartModel> findAll() {
        return jdbcTemplate.query(
                SELECT_COLUMNS + " ORDER BY part_id",
                this::mapPart);
    }

    /**
     * Reads one part using a bound identifier.
     * @param id part identifier
     * @return matching part, or empty
     */
    @Override
    public Optional<PartModel> findById(Long id) {
        List<PartModel> matches = jdbcTemplate.query(
                SELECT_COLUMNS + " WHERE part_id = ?",
                this::mapPart,
                id);

        return matches.stream().findFirst();
    }

    /**
     * Updates editable values without changing the identifier.
     * @param part validated values and identifier
     * @return whether a matching row was updated
     */
    @Override
    @Transactional
    public boolean update(PartModel part) {
        String sql = "UPDATE parts SET "
                + "part_name = ?, category = ?, manufacturer = ?, model = ?, "
                + "quantity = ?, unit_cost = ?, storage_location = ?, "
                + "description = ?, version = version + 1 "
                + "WHERE part_id = ?";

        // Incrementing version also makes an unchanged form a real row update.
        int rows = jdbcTemplate.update(
                sql,
                part.getPartName(),
                part.getCategory(),
                part.getManufacturer(),
                part.getModel(),
                part.getQuantity(),
                part.getUnitCost(),
                part.getStorageLocation(),
                part.getDescription(),
                part.getPartId());

        return rows == 1;
    }

    /**
     * Deletes only the row matching the supplied identifier.
     * @param id part identifier
     * @return whether a row was deleted
     */
    @Override
    @Transactional
    public boolean delete(Long id) {
        return jdbcTemplate.update(
                "DELETE FROM parts WHERE part_id = ?", id) == 1;
    }

    /**
     * Maps a database row to the application's part model.
     * @param results current result row
     * @param rowNumber result row index
     * @return populated part
     * @throws SQLException when a column cannot be read
     */
    private PartModel mapPart(ResultSet results, int rowNumber)
            throws SQLException {
        PartModel part = new PartModel();

        part.setPartId(results.getLong("part_id"));
        part.setPartName(results.getString("part_name"));
        part.setCategory(results.getString("category"));
        part.setManufacturer(results.getString("manufacturer"));
        part.setModel(results.getString("model"));
        part.setQuantity(results.getInt("quantity"));
        part.setUnitCost(results.getBigDecimal("unit_cost"));
        part.setStorageLocation(results.getString("storage_location"));
        part.setDescription(results.getString("description"));

        return part;
    }
}