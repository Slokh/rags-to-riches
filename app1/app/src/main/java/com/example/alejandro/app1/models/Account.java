package com.example.alejandro.app1.models;

/**
 * Written by: Kartik Patel
 *
 * Account class to store user information
 */

public class Account {

    int id;
    String email;
    String username;
    String password;

    /**
     *
     * @param id        user's id
     * @param email     user's email
     * @param username  user's name
     * @param password  user's password
     */
    public Account(int id, String email, String username, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     *
     * @return the user's id number
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return the user's name
     */
    public String getUsername() { return username; }

    /**
     *
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

}
