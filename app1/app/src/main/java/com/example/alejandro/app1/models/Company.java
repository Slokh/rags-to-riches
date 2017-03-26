package com.example.alejandro.app1.models;

/**
 * Created by Kartik on 3/17/2017.
 */

public class Company {
    int counter = 0;
    String name;
    String ticker;
    String description;
    String realName;
    double price;
    double[] priceHistory;

    public Company(String name, String ticker, String realName, double[] priceHistory) {
        this.name = name;
        this.ticker = ticker;
        this.realName = realName;
        this.priceHistory = priceHistory;
        this.price = priceHistory[1];
        this.description = "";
    }

    public String getName() {
        return name;
    }

    public double getPrice() { return price; }

    public double getPriceAt(int i) {
        return priceHistory[i];
    }

    public double[] getPriceHistory() {
        return priceHistory;
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

    public void setPrice(double price) {
        this.price = price;
    }
}
