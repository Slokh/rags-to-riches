package com.example.alejandro.app1.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kartik on 3/17/2017.
 */

public class Portfolio {

    String userName;
    int gameID;
    int rank;
    int credits;
    Map<Company, Integer> stocks;

    public Portfolio(String userName, int gameID, int rank, int credits) {
        this.userName = userName;
        this.gameID = gameID;
        this.rank = rank;
        this.credits = credits;
        stocks = new HashMap<Company, Integer>();
    }

    public String getUserName() {
        return userName;
    }

    public int getGameID() {
        return gameID;
    }

    public int getRank() {
        return rank;
    }

    public int getCredits() {
        return credits;
    }

    public Map<Company, Integer> getStocks() {
        return stocks;
    }

    public int getAmountOfStock(Company company) {
        return stocks.get(company);
    }

    public void updateStock(Company company, int amount) {
        stocks.put(company, amount);
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
