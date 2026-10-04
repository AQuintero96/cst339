-- IT Parts Inventory Manager
-- Author: Alex Quintero
-- Milestone 4: Database persistence
--
-- Creates the database and tables without dropping existing data.
-- IF NOT EXISTS does not update the structure of existing tables.

CREATE DATABASE IF NOT EXISTS it_parts_inventory
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE it_parts_inventory;

-- Stores registered accounts.
-- Password confirmation and plaintext passwords are never stored.
CREATE TABLE IF NOT EXISTS app_users (
    user_id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(254) NOT NULL,
    phone_number CHAR(10) NOT NULL,
    username VARCHAR(32) NOT NULL,

    -- Encoded password hash, including algorithm parameters and salt.
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
) ENGINE = InnoDB;

-- Stores parts in the shared inventory.
CREATE TABLE IF NOT EXISTS parts (
    part_id BIGINT NOT NULL AUTO_INCREMENT,
    part_name VARCHAR(100) NOT NULL,
    category VARCHAR(30) NOT NULL,
    manufacturer VARCHAR(60) NOT NULL,
    model VARCHAR(100) NOT NULL,
    quantity INT NOT NULL DEFAULT 0,
    unit_cost DECIMAL(12, 2) NOT NULL,
    storage_location VARCHAR(100) NOT NULL,
    description VARCHAR(1000),

    -- Reserved for update conflict detection in a later milestone.
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
) ENGINE = InnoDB;

-- Parts belong to the shared inventory, not individual users.
-- Application validation also checks field formats and numeric limits.