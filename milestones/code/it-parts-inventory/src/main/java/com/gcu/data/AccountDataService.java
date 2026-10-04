package com.gcu.data;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gcu.model.AccountRecord;
import com.gcu.model.UserModel;

/**
 * Stores and retrieves accounts using parameterized Spring JDBC queries.
 */
@Repository
public class AccountDataService implements AccountDataAccessInterface {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Receives Spring's configured JDBC template.
     *
     * @param jdbcTemplate the database access helper
     */
    public AccountDataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Inserts an account without storing the submitted plaintext password.
     *
     * @param user the validated registration information
     * @param passwordHash the encoded password credentials
     */
    @Override
    public void create(UserModel user, String passwordHash) {
        String sql = "INSERT INTO app_users "
                + "(first_name, last_name, email, phone_number, "
                + "username, password_hash) VALUES (?, ?, ?, ?, ?, ?)";

        // The unique username constraint also prevents concurrent duplicates.
        jdbcTemplate.update(
                sql,
                user.getFirstName().trim(),
                user.getLastName().trim(),
                user.getEmail().trim(),
                user.getPhoneNumber(),
                user.getUsername().trim().toLowerCase(Locale.ROOT),
                passwordHash);
    }

    /**
     * Retrieves the fields needed to authenticate and establish a session.
     *
     * @param username the submitted username
     * @return the account when found
     */
    @Override
    public Optional<AccountRecord> findByUsername(String username) {
        String sql = "SELECT user_id, username, first_name, password_hash "
                + "FROM app_users WHERE username = ?";

        List<AccountRecord> accounts = jdbcTemplate.query(
                sql,
                (results, rowNumber) -> new AccountRecord(
                        results.getLong("user_id"),
                        results.getString("username"),
                        results.getString("first_name"),
                        results.getString("password_hash")),
                username.trim().toLowerCase(Locale.ROOT));

        return accounts.stream().findFirst();
    }
}