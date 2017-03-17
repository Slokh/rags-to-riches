package com.example.alejandro.app1.models;

/**
 * Created by Kartik on 3/17/2017.
 */

public class Company {

    String name;
    String ticker;
    String description;
    String realName;
    int price;

    public Company(String name, String ticker, String description, String realName, int price) {
        this.name = name;
        this.ticker = ticker;
        this.description = description;
        this.realName = realName;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getTicker() {
        return ticker;
    }

    public String getDescription() {
        return description;
    }

    public String getRealName() {
        return realName;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
