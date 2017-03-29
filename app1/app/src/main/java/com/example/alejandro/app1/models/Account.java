package com.example.alejandro.app1.models;

/**
 * Created by Kartik on 3/17/2017.
 */

public class Account {

    int id;
    String email;
    String username;
    String password;

    public Account(int id, String email, String username, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() { return username; }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
