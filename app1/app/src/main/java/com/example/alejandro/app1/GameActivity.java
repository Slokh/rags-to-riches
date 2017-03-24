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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;

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
import java.util.List;

/**
 * Created by Kartik on 3/20/2017.
 */

public class GameActivity extends MainMenuActivity {

    ArrayAdapter<Company> displayAdapter;
    private Button mQuitButton = null;

    int amountOfCompanies = 10;
    List<Company> companies;
    double[] prices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        prices = new double[amountOfCompanies];
        companies = new ArrayList<Company>();
        generateCompanies();

        Context context = getApplicationContext();
        displayAdapter = new CompanyAdapter(GameActivity.this, context, companies);

        ListView listView = (ListView) findViewById(R.id.companyList);
        listView.setAdapter(displayAdapter);
        listView.setBackgroundColor(Color.WHITE);

        mQuitButton = (Button) findViewById(R.id.goMainMenuActivity);
        mQuitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),MainMenuActivity.class);
                startActivity(i);
            }
        });
    }

    public void generateCompanies() {
        try {
            URL url = new URL("http://parallel.gg/rags-to-riches/generate-companies.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("amount","UTF-8")+"="+URLEncoder.encode("" + amountOfCompanies,"UTF-8");
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
            companies.add(new Company(gameName, ticker, realName, prices));
        }
    }
}
