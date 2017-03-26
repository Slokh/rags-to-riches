package com.example.alejandro.app1.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kartik on 3/17/2017.
 */

public class Portfolio {


    double balance;
    int id;
    Map<Company, Integer> stocks;

    public Portfolio(int id, Map<Company, Integer> stocks, double balance) {
        this.id = id;
        this.stocks = stocks;
        this.balance = balance;
    }

    public int getId() { return  id; }

    public Map<Company, Integer> getStocks() {
        return stocks;
    }

    public int getAmountOfStock(Company company) {
        return stocks.get(company);
    }

    public void updateStock(Company company, int amount) {
        stocks.put(company, amount);
    }

    public double getBalance(){
        return balance;
    }

    public void updateBalance(double priceAt, int amountofstocks, boolean buyorSell ){
        if (buyorSell == true){
            balance = balance - amountofstocks*priceAt;
        }
        if (buyorSell == false){
            balance = balance + amountofstocks*priceAt;
        }
    }




}
