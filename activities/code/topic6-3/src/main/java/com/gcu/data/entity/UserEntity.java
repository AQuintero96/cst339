package com.gcu.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Maps a login account to the users collection.
 * The password property contains a BCrypt hash, not plaintext.
 */
@Document(collection = "users")
public class UserEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    private String password;

    /** Creates an empty account for MongoDB mapping. */
    public UserEntity() {
    }

    /**
     * Creates an account.
     * @param id document identifier
     * @param username login name
     * @param password BCrypt password hash
     */
    public UserEntity(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /** Returns the document identifier. */
    public String getId() {
        return id;
    }

    /** Sets the document identifier. */
    public void setId(String id) {
        this.id = id;
    }

    /** Returns the login name. */
    public String getUsername() {
        return username;
    }

    /** Sets the login name. */
    public void setUsername(String username) {
        this.username = username;
    }

    /** Returns the stored BCrypt hash. */
    public String getPassword() {
        return password;
    }

    /** Sets the stored BCrypt hash. */
    public void setPassword(String password) {
        this.password = password;
    }
}