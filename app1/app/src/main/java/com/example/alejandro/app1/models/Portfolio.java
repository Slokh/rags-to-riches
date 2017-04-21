package com.example.alejandro.app1.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.NumberFormat;
/**
 * Created by Kartik on 3/17/2017.
 * Portfolio class to store information regarding a user's portfolio
 */

public class Portfolio {

    double balance;
    int id;
    Map<Company, Integer> stocks;
    List<Double> turnBalanceHistory;

    /**
     *
     * @param id        user's id number
     * @param stocks    stocks the user owns
     * @param balance   user's balance
     */
    public Portfolio(int id, Map<Company, Integer> stocks, double balance) {
        this.id = id;
        this.stocks = stocks;
        this.balance = balance;
        this.turnBalanceHistory = new ArrayList<Double>();
    }

    /**
     *
     * @return the user's id
     */
    public int getId() { return  id; }

    /**
     *
     * @return the stocks owned by the user
     */
    public Map<Company, Integer> getStocks() {
        return stocks;
    }

    /**
     *
     * @param company   a specific company in the game
     * @return          the amount of stocks owned by the user of that company
     */
    public int getAmountOfStock(Company company) {
        return stocks.get(company);
    }

    /**
     *
     * @param company   a specific company in the game
     * @param amount    amount of stocks for the specificed company
     */
    public void updateStock(Company company, int amount) {
        stocks.put(company, amount);
    }

    /**
     *
     * @return the user's balance
     */
    public double getBalance(){
        return balance;
    }

    /**
     *
     * @return a text version of the user's balance
     */
    public String getBalanceText(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(balance);
    }

    public String getWebText() {
        String blnce = balance + "";
        return blnce.replace(".", "");
    }

    /**
     *
     * @param priceAt           the price of the stock
     * @param amountofstocks    the amount of stocks to buy or sell
     * @param buyorSell         true = buy stocks, false = sell stocks
     */
    public void updateBalance(double priceAt, int amountofstocks, boolean buyorSell ){
        if (buyorSell == true){
            balance = balance - amountofstocks*priceAt;
        }
        if (buyorSell == false){
            balance = balance + amountofstocks*priceAt;
        }
    }

    /**
     *
     * @param turn  a turn in the game
     * @return  the user's balance at a specified turn
     */
    public double getBalanceAt(int turn) {
        return turnBalanceHistory.get(turn);
    }

    /**
     * update the user's balance for a specific turn
     */
    public void addTurnBalance() {
        turnBalanceHistory.add(balance);
    }

}
