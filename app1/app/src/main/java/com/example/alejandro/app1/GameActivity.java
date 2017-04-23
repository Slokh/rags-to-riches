package com.example.alejandro.app1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.LoaderManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.EditText;
import com.example.alejandro.app1.adapters.CompanyAdapter;
import com.example.alejandro.app1.models.Company;
import android.view.ViewGroup;
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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.example.alejandro.app1.models.Account;
import com.example.alejandro.app1.models.Portfolio;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import static android.R.attr.data;
import static android.R.attr.port;
import static android.R.id.list;
import static android.R.id.message;
import static android.media.CamcorderProfile.get;
import static com.example.alejandro.app1.R.drawable.company;
import static com.example.alejandro.app1.R.id.price;
import static com.example.alejandro.app1.R.id.relativeLayout;
import static java.sql.Types.NULL;
import android.view.View;
import android.widget.PopupWindow;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.content.Intent;

/**
 * GameActivity class handles all functions in regards to the game itself
 */

public class GameActivity extends MainMenuActivity {

    ArrayAdapter<Company> displayAdapter;
    TextView turnCount;
    TextView balanceAmount;
    private Button mQuitButton = null;
    private Button mPortfolioButton = null;
    private Button mNextTurnButton = null;
    private Button mStandingsButton = null;

    private Button test;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    double Standingsarray[];
    int amountOfCompanies = 10;
    List<Company> companies;
    double[] prices;
    Account account;
    Portfolio portfolio;
    int timercount = 30;
    int turnvalue = 10;
    int turn = 1;
    double initialbalance = 5000;

    String code;

    /**
     * General initializer of Android Activity
     * @param savedInstanceState    saved Instance of previous activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

/*

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

*/





        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        final Bundle extras = getIntent().getExtras();
        account = new Account(extras.getInt("id"), extras.getString("email"), extras.getString("username"), extras.getString("password"));
        code = extras.getString("code");

        turnCount = (TextView) findViewById(R.id.turnCount);
        turnCount.setText("Turn: " + String.valueOf(turnvalue-9));


        mNextTurnButton = (Button) findViewById(R.id.editRemainingTimeTextButton);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        prices = new double[amountOfCompanies];
        companies = new ArrayList<Company>();
        generateCompanies();
        portfolio = grabPortfolio();


        balanceAmount = (TextView) findViewById(R.id.balanceAmount);
        balanceAmount.setText("Balance: " + portfolio.getBalanceText());

        final Context context = getApplicationContext();
        displayAdapter = new CompanyAdapter(GameActivity.this, context, account, portfolio, companies, turnvalue);

        ListView listView = (ListView) findViewById(R.id.companyList);
        listView.setAdapter(displayAdapter);
        balanceAmount.setText("Your Balance: " + portfolio.getBalance());
        listView.setBackgroundColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
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

        //






        mQuitButton = (Button) findViewById(R.id.goMainMenuActivity);
        mQuitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),MainMenuActivity.class);
                startActivity(i);
            }
        });

        mPortfolioButton = (Button) findViewById(R.id.portfolioButton);
        mPortfolioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(GameActivity.this).create();
                alertDialog.setTitle("Portfolio");

                String message = "";
                for(Company c : portfolio.getStocks().keySet()) {
                    message += c.getName() + ": " + "" + portfolio.getAmountOfStock(c) + "\n";
                }

                alertDialog.setMessage(message);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogSuccess, int which) {
                                dialogSuccess.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });


        mStandingsButton = (Button) findViewById(R.id.standingsButton);

        mStandingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(GameActivity.this).create();
                alertDialog.setTitle("Standings");


                // player 2, 3, 4 balance amounts should update here from server:
                //
                //


                double Standingsarray[] = {(Math. random() * 9999 + 1000),(Math. random() * 9999 + 1000),(Math. random() * 9999 + 1000)};

                //
                //
                NumberFormat formatter = NumberFormat.getCurrencyInstance();


                String message = "You: " + formatter.format(portfolio.getBalance()) + "\nPlayer 2: "+ formatter.format(Standingsarray[0]) + " \nPlayer 3: " + formatter.format(Standingsarray[1]) + "\nPlayer 4: " + formatter.format(Standingsarray[2]) +"\n";


                alertDialog.setMessage(message);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogSuccess, int which) {
                                dialogSuccess.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });

        // turn timer stuff //

        mNextTurnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(turn < 24) {
                    for (int x = 0; x < companies.size(); x++) {
                        (companies.get(x)).goToNextWeek();
                    }
                    turn++;
                    displayAdapter = new CompanyAdapter(GameActivity.this, context, account, portfolio, companies, companies.get(0).returnWeek());

                    ListView listView = (ListView) findViewById(R.id.companyList);
                    listView.setAdapter(displayAdapter);
                    //              balanceAmount.setText("Your Balance: " + portfolio.getBalance());
                    //             Log.d("SUP",Double.toString(portfolio.getBalance()));

                    turnCount.setText("Turn: " + String.valueOf(turn));

                    updatePortfolio();
                } else if(turn == 24) {
                    turn++;
                    mNextTurnButton.setText("Finish");
                    turnCount.setText("Turn: " + String.valueOf(turn));
                    updatePortfolio();
                } else if(turn == 25) {
                    sellEverything();
                    updatePortfolio();
                    Intent i = new Intent(view.getContext(),WaitEndActivity.class);
                    i.putExtra("id", extras.getInt("id"));
                    i.putExtra("email", extras.getString("email"));
                    i.putExtra("username", extras.getString("username"));
                    i.putExtra("password", extras.getString("password"));
                    startActivity(i);
                }

            }
        });


        Thread balanceUpdater = new Thread(){
            @Override public void run(){
                while(!isInterrupted()){
                    try {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() { @Override public void run() {
                            balanceAmount.setText("Balance: " + portfolio.getBalanceText());
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

    public void sellEverything() {
        for(Company c : companies) {
            int amt = portfolio.getAmountOfStock(c);
            portfolio.updateStock(c, 0);
            portfolio.updateBalance(c.getPrice(), amt, false);
        }
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
            companies.add(new Company(gameName, ticker, realName, prices, turnvalue));
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
            post_data += "&" + URLEncoder.encode("b","UTF-8")+"="+URLEncoder.encode(portfolio.getWebText(),"UTF-8");
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
     * Get a specific company
     * @param companyName   Name of a company
     * @return  the company object from the given name
     */

    @Override
    public void onBackPressed() { }

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




    //    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){

//            case R.id.home:
 //               NavUtils.navigateUpFromSameTask(this);
  //              return true;

            case R.id.action_settings:

                Intent k = new Intent(GameActivity.this, SettingsActivity.class);
                startActivity(k);
                return true;
            case R.id.action_achievements:
                //stuff
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }


}
