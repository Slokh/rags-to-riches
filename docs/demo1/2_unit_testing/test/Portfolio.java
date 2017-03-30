package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.NumberFormat;
/**
 * Created by Kartik on 3/17/2017.
 */

public class Portfolio {


    double balance;
    int id;
    Map<Company, Integer> stocks;
    List<Double> turnBalanceHistory;

    public Portfolio(int id, Map<Company, Integer> stocks, double balance) {
        this.id = id;
        this.stocks = stocks;
        this.balance = balance;
        this.turnBalanceHistory = new ArrayList<Double>();
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

    public String getBalanceText(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(balance);
    }

    public void updateBalance(double priceAt, int amountofstocks, boolean buyorSell ){
        if (buyorSell == true){
            balance -= (amountofstocks*priceAt);
        }
        else{
            balance -= (amountofstocks*priceAt);
        }
    }

    public double getBalanceAt(int turn) {
        return turnBalanceHistory.get(turn);
    }

    public void addTurnBalance() {
        turnBalanceHistory.add(balance);
    }

}
