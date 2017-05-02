package com.example.alejandro.app1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.alejandro.app1.adapters.CompanyAdapter;
import com.example.alejandro.app1.models.Company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.example.alejandro.app1.models.Account;
import com.example.alejandro.app1.models.Portfolio;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import static android.R.id.message;


/**
 * Written by: Kartik Patel, Deep Patel, Alejandro Aguilar, Arjun Ohri
 * Tested/Debugged by: Kartik Patel, Deep Patel
 *
 * GameActivity class handles all functions regarding the actual game itself. It is
 * responsible for continuously updating the database based on user's transactions as well
 * as displaying the correct information to the user and handling user input
 *
 */

public class GameActivity extends MainMenuActivity {

    Account account;
    Portfolio portfolio;
    String code;

    double[] prices;
    List<Company> companies;
    int amountOfCompanies = 10;
    int turn = 1;

    TextView turnCount;
    TextView balanceAmount;
    private Button mPortfolioButton = null;
    private Button mNextTurnButton = null;
    private Toolbar toolbar;
    ArrayAdapter<Company> displayAdapter;
    /**
     * General initializer of Android Activity
     * @param savedInstanceState    saved Instance of previous activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final Bundle extras = getIntent().getExtras();
        account = new Account(extras.getInt("id"), extras.getString("email"), extras.getString("username"), extras.getString("password"));
        code = extras.getString("code");

        prices = new double[amountOfCompanies];
        companies = new ArrayList<Company>();
        generateCompanies();
        portfolio = grabPortfolio();

        final Context context = getApplicationContext();
        displayAdapter = new CompanyAdapter(GameActivity.this, context, account, portfolio, companies, turn+9);

        ListView listView = (ListView) findViewById(R.id.companyList);
        listView.setAdapter(displayAdapter);
        listView.setBackgroundColor(Color.WHITE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Company company = companies.get((int) id);

            final Dialog infoDidalog = new Dialog(GameActivity.this);
            infoDidalog.setTitle("Stock Info");
            infoDidalog.setContentView(R.layout.stock_info);
            infoDidalog.show();

            GraphView graph = (GraphView) infoDidalog.findViewById(R.id.graph);
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, company.getPriceAt(company.returnWeek()-9)),
                new DataPoint(2, company.getPriceAt(company.returnWeek()-8)),
                new DataPoint(3, company.getPriceAt(company.returnWeek()-7)),
                new DataPoint(4, company.getPriceAt(company.returnWeek()-6)),
                new DataPoint(5, company.getPriceAt(company.returnWeek()-5)),
                new DataPoint(6, company.getPriceAt(company.returnWeek()-4)),
                new DataPoint(7, company.getPriceAt(company.returnWeek()-3)),
                new DataPoint(8, company.getPriceAt(company.returnWeek()-2)),
                new DataPoint(9, company.getPriceAt(company.returnWeek()-1)),
            });

            graph.setTitle(company.getName() + " Trends");
            graph.addSeries(series);
            graph.getViewport().setMinX(1);

            graph.getViewport().setScalable(true);
            graph.getViewport().setScrollable(true);

            graph.getViewport().setScalableY(true);
            graph.getViewport().setScrollableY(true);

            graph.getGridLabelRenderer().setNumHorizontalLabels(9);
            graph.getViewport().setXAxisBoundsManual(true);

            graph.getViewport().setMinX(1);
            graph.getViewport().setMaxX(9);

            }
        });

        turnCount = (TextView) findViewById(R.id.turnCount);
        turnCount.setText("Turn: " + String.valueOf(turn));

        balanceAmount = (TextView) findViewById(R.id.balanceAmount);
        balanceAmount.setText(portfolio.getBalanceText());

        mPortfolioButton = (Button) findViewById(R.id.portfolioButton);
        mPortfolioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alertDialog = new AlertDialog.Builder(GameActivity.this).create();
                alertDialog.setTitle("Portfolio");

                String display = "";
                for(Company c : portfolio.getStocks().keySet()) {
                    if(portfolio.getAmountOfStock(c) > 0) {
                        display += c.getName() + ": " + "" + portfolio.getAmountOfStock(c) + "\n";
                    }
                }
                alertDialog.setMessage(display);

                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogSuccess, int which) {
                        dialogSuccess.dismiss();
                        }
                    });

                alertDialog.show();
            }
        });

        mNextTurnButton = (Button) findViewById(R.id.editRemainingTimeTextButton);
        mNextTurnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if(turn == 25) {

                sellEverything();
                updatePortfolio();

                Intent i = new Intent(view.getContext(),WaitEndActivity.class);
                i.putExtra("id", extras.getInt("id"));
                i.putExtra("email", extras.getString("email"));
                i.putExtra("username", extras.getString("username"));
                i.putExtra("password", extras.getString("password"));
                startActivity(i);

            } else {

                for (int x = 0; x < companies.size(); x++) {
                    (companies.get(x)).goToNextWeek();
                }

                turn++;
                updatePortfolio();

                turnCount.setText(turn == 24 ? "Finish" : "Turn: " + String.valueOf(turn));

                displayAdapter = new CompanyAdapter(GameActivity.this, context, account, portfolio, companies, companies.get(0).returnWeek());
                ListView listView = (ListView) findViewById(R.id.companyList);
                listView.setAdapter(displayAdapter);

            }

            }
        });


        Thread balanceUpdater = new Thread(){
            @Override public void run(){
            while(!isInterrupted()){
                try {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() { @Override public void run() {
                        balanceAmount.setText(portfolio.getBalanceText());
                    }
                    });
                }catch(InterruptedException e ){
                    e.printStackTrace();
                }
            }
            }

        };
        balanceUpdater.start();
    }


    /**
     * Generates a List of 10 companies to be used in our game and maps them to real life equivalents
     * Data on the company is downloaded from our database and onto the user's device
     */
    public void generateCompanies() {
        try {
            URL url = new URL("http://parallel.gg/rags-to-riches/grab-companies.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("amount","UTF-8")+"="+URLEncoder.encode("" + amountOfCompanies,"UTF-8");
            post_data += "&" + URLEncoder.encode("code","UTF-8")+"="+URLEncoder.encode("" + code,"UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result="";
            String line="";
            while((line = bufferedReader.readLine())!= null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            parseCompanies(result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses the CSV file returned from the database and creates company objects based on the data
     * @param result
     */
    public void parseCompanies(String result) {
        String[] companyObjects = result.split("/");
        for(String companyObject : companyObjects) {
            String[] split = companyObject.split(",");
            String realName = split[0];
            String gameName = split[1];
            String ticker = split[2];
            double[] prices = new double[50];
            for(int i=0; i<50; i++) {
                prices[i] = Double.parseDouble(split[i+3]);
            }
            companies.add(new Company(gameName, ticker, realName, prices, turn+9));
        }
    }

    /**
     * Grabs a user's portfolio from the database
     * @return a generated portfolio constructed from the returned data
     */
    public Portfolio grabPortfolio() {
        try {
            URL url = new URL("http://parallel.gg/rags-to-riches/grab-portfolio.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode("" + account.getId(),"UTF-8");
            int i = 0;
            for(Company c : companies) {
                post_data += "&" + URLEncoder.encode("c"+i,"UTF-8")+"="+URLEncoder.encode("" + c.getRealName(),"UTF-8");
                i++;
            }
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result="";
            String line="";
            while((line = bufferedReader.readLine())!= null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return new Portfolio(account.getId(), parsePortfolio(result), Double.parseDouble(result.split("/")[0]));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Update the player's portfolio in the database at the end of each turn with their
     * transactions in that turn
     */
    public void updatePortfolio() {
        try {
            URL url = new URL("http://parallel.gg/rags-to-riches/update-portfolio.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode("" + account.getId(),"UTF-8");
            post_data += "&" + URLEncoder.encode("t","UTF-8")+"="+URLEncoder.encode("" + turn,"UTF-8");
            post_data += "&" + URLEncoder.encode("d","UTF-8")+"="+URLEncoder.encode(portfolio.getDollars(),"UTF-8");
            post_data += "&" + URLEncoder.encode("c","UTF-8")+"="+URLEncoder.encode(portfolio.getCents(),"UTF-8");
            int i = 0;
            for(Company c : companies) {
                post_data += "&" + URLEncoder.encode("c"+i,"UTF-8")+"="+URLEncoder.encode("" + c.getRealName(),"UTF-8");
                post_data += "&" + URLEncoder.encode("d"+i,"UTF-8")+"="+URLEncoder.encode("" + portfolio.getAmountOfStock(c),"UTF-8");
                i++;
            }
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result="";
            String line="";
            while((line = bufferedReader.readLine())!= null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parse the portfolio CSV into a map of companies and their stocks
     * @param result CSV to be parsed
     * @return  Map of companies and their stocks
     */
    public HashMap<Company, Integer> parsePortfolio(String result) {
        HashMap<Company, Integer> map = new HashMap<Company, Integer>();
        String[] split = result.split("/");
        for(int i=1; i<split.length; i++) {
            map.put(getCompany(split[i].split(",")[0]), Integer.parseInt(split[i].split(",")[1]));
        }
        return map;
    }

    /**
     * Sells all stocks owned by the user. Used in Turn 25 to get rid of all stocks and
     * prepare for the end of the game.
     */
    public void sellEverything() {
        for(Company c : companies) {
            int amt = portfolio.getAmountOfStock(c);
            portfolio.updateStock(c, 0);
            portfolio.updateBalance(c.getPrice(), amt, false);
        }
    }

    /**
     * Get a specific company based on the company name
     * @param companyName   name of the company
     * @return              Company object based on company name
     */
    public Company getCompany(String companyName) {
        for(Company company : companies) {
            if(company.getRealName().equals(companyName)) return company;
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.gamemenu,menu);
        return true;

    }

}
