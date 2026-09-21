-- IT Parts Inventory Manager
-- Author: Alex Quintero
-- Milestone 2: Proposed database schema
--
-- DESIGN ONLY:
-- This script is not connected to the Milestone 2 application.
-- Database implementation and execution testing are deferred to Milestone 4.
--
-- Target: MySQL 8.0.16 or later.
-- Run against a selected project database when persistence is implemented.
-- No database accounts, credentials, or sample passwords are included.

-- Stores registered users and their encoded password credentials.
-- Username comparison is case-insensitive, matching the current application.
-- Password confirmation is a form-only field and is never persisted.
CREATE TABLE app_users (
    user_id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(254) NOT NULL,
    phone_number CHAR(10) NOT NULL,
    username VARCHAR(32) NOT NULL,

    -- Planned encoded password value, including algorithm parameters and salt.
    -- Persistence code must produce and verify this format.
    password_hash VARCHAR(255) NOT NULL,

    PRIMARY KEY (user_id),
    CONSTRAINT uq_app_users_username UNIQUE (username),

    CONSTRAINT chk_users_first_name
        CHECK (CHAR_LENGTH(TRIM(first_name)) > 0),

    CONSTRAINT chk_users_last_name
        CHECK (CHAR_LENGTH(TRIM(last_name)) > 0),

    CONSTRAINT chk_users_username_length
        CHECK (CHAR_LENGTH(username) BETWEEN 3 AND 32),

    CONSTRAINT chk_users_phone_length
        CHECK (CHAR_LENGTH(phone_number) = 10)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

-- Stores computer part models at their inventory locations.
-- Each row represents a part model at a location, with an available quantity.
-- Parts belong to the shared inventory rather than individual user accounts.
CREATE TABLE parts (
    part_id BIGINT NOT NULL AUTO_INCREMENT,
    part_name VARCHAR(100) NOT NULL,
    category VARCHAR(30) NOT NULL,
    manufacturer VARCHAR(60) NOT NULL,
    model VARCHAR(100) NOT NULL,
    quantity INT NOT NULL DEFAULT 0,
    unit_cost DECIMAL(12, 2) NOT NULL,
    storage_location VARCHAR(100) NOT NULL,
    description VARCHAR(1000),

    -- Reserved for optimistic locking when persistence is implemented.
    version BIGINT NOT NULL DEFAULT 0,

    PRIMARY KEY (part_id),

    CONSTRAINT chk_parts_name
        CHECK (CHAR_LENGTH(TRIM(part_name)) > 0),

    CONSTRAINT chk_parts_category
        CHECK (
            category IN (
                'RAM',
                'SSD',
                'Hard Drive',
                'Processor',
                'Motherboard',
                'Power Supply',
                'Graphics Card',
                'Cooling'
            )
        ),

    CONSTRAINT chk_parts_manufacturer
        CHECK (CHAR_LENGTH(TRIM(manufacturer)) > 0),

    CONSTRAINT chk_parts_model
        CHECK (CHAR_LENGTH(TRIM(model)) > 0),

    CONSTRAINT chk_parts_quantity
        CHECK (quantity >= 0),

    CONSTRAINT chk_parts_unit_cost
        CHECK (unit_cost >= 0),

    CONSTRAINT chk_parts_location
        CHECK (CHAR_LENGTH(TRIM(storage_location)) > 0),

    CONSTRAINT chk_parts_version
        CHECK (version >= 0)
) ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

-- Additional application validation will enforce email format,
-- phone digits, username characters, and password rules.
--
-- No user-to-part foreign key is included because the proposal specifies
-- one shared inventory without individual ownership of part records.