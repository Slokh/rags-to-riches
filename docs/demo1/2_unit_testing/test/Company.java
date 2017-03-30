package test;

import java.text.NumberFormat;

/**
 * Created by Kartik on 3/17/2017.
 */

public class Company {
    int currentWeek;
    String name;
    String ticker;
    String description;
    String realName;
    double price;
    double[] priceHistory;

    public Company(String name, String ticker, String realName, double[] priceHistory, int turnvalue) {
        this.name = name;
        this.ticker = ticker;
        this.realName = realName;
        this.priceHistory = priceHistory;
        this.price = priceHistory[turnvalue];
        this.description = "";
        this.currentWeek = turnvalue;
    }

    public String getName() {
        return name;
    }

    public double getPrice() { return price; }

    public String getPriceText(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(price);
    }

    public int returnWeek() { return currentWeek;}

    public void goToNextWeek() { currentWeek++; price = priceHistory[currentWeek]; }

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
