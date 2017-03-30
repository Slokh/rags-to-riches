package com.example.alejandro.app1.models;

import java.text.NumberFormat;

/**
 * Created by Kartik on 3/17/2017.
 * Company class to store company information
 */

public class Company {
    int currentWeek;
    String name;
    String ticker;
    String description;
    String realName;
    double price;
    double[] priceHistory;

    /**
     *
     * @param name          the name of the company
     * @param ticker        shorthand of the company's name
     * @param realName      the real-life equivalent of the company
     * @param priceHistory  array storing week-by-week stock prices of the company
     * @param turnvalue     starting turn for the game
     */
    public Company(String name, String ticker, String realName, double[] priceHistory, int turnvalue) {
        this.name = name;
        this.ticker = ticker;
        this.realName = realName;
        this.priceHistory = priceHistory;
        this.price = priceHistory[turnvalue];
        this.description = "";
        this.currentWeek = turnvalue;
    }

    /**
     *
     * @return the company's name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the company's price at current week
     */
    public double getPrice() { return price; }

    /**
     *
     * @return a text version of the company's price
     */
    public String getPriceText(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(price);
    }

    /**
     *
     * @return the current week in the game
     */
    public int returnWeek() { return currentWeek;}

    /**
     *  Advance one week in the game
     */
    public void goToNextWeek() { currentWeek++; price = priceHistory[currentWeek]; }

    /**
     *
     * @param i Value representing a specific week
     * @return the price of the company at the ith week
     */
    public double getPriceAt(int i) {
        return priceHistory[i];
    }

    /**
     *
     * @return the array of price history for the company
     */
    public double[] getPriceHistory() {
        return priceHistory;
    }

    /**
     *
     * @return the shorthand ticker name
     */
    public String getTicker() {
        return ticker;
    }

    /**
     *
     * @return the description of the company
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return the real-life equivalent of the company
     */
    public String getRealName() {
        return realName;
    }

    /**
     *
     * @param price the price at which the company should be set to
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
