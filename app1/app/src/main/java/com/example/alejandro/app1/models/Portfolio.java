package com.example.alejandro.app1.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kartik on 3/17/2017.
 */

public class Portfolio {

    int id;
    Map<Company, Integer> stocks;

    public Portfolio(int id, Map<Company, Integer> stocks) {
        this.id = id;
        this.stocks = stocks;
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
}
