package com.example.alejandro.app1.models;

/**
 * Created by Kartik on 3/17/2017.
 */

public class Account {

    String email;
    String password;
    String userName;

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
