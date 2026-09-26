-- IT Parts Inventory Manager
-- Author: Alex Quintero
-- Milestone 3: Proposed database schema
--
-- DESIGN ONLY:
-- The application does not connect to this database.
-- Database implementation and SQL execution testing are planned
-- for Milestone 4.
--
-- Target: MySQL 8.0.16 or later.
-- Select the project database before running this script.

-- Stores account information and encoded password credentials.
-- Password confirmation belongs only to the registration form.
CREATE TABLE app_users (
    user_id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(254) NOT NULL,
    phone_number CHAR(10) NOT NULL,
    username VARCHAR(32) NOT NULL,

    -- Future persistence code must encode the hash, salt,
    -- and algorithm parameters into this value.
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

-- Stores parts in the shared inventory.
-- Each row describes a component at a storage location.
-- Field lengths and numeric limits match the part creation model.
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

    -- Reserved for detecting conflicting updates once persistence
    -- is implemented. This field is not in the current PartModel.
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

-- Application validation also checks email format, phone digits,
-- username characters, and password requirements.
--
-- No user-to-part foreign key is included because all users work
-- with the same inventory. Parts do not belong to individual users.
--
-- Milestone 3 assigns temporary part IDs in the business service.
-- Database-generated IDs will replace them when persistence is added.