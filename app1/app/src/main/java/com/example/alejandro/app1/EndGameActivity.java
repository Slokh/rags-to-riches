package com.example.alejandro.app1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

/**
 * EndGameActivity handles all functions regarding the end-game display. Once the game has ended,
 * this class is responsible for displaying the end-game results and showcasing the winner
 *
 * Created by Kartik on 4/22/2017.
 */

public class EndGameActivity extends MainMenuActivity {

    TextView firstName;
    TextView secondName;
    private Button backButton = null;

    /**
     * General initializer of Android Activity
     * @param savedInstanceState    saved Instance of previous activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_end_game);

        final Bundle extras = getIntent().getExtras();

        firstName = (TextView) findViewById(R.id.firstName);
        secondName = (TextView) findViewById(R.id.secondName);
        backButton = (Button) findViewById(R.id.backButton);

        getResults();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), MainMenuActivity.class);
                i.putExtra("id", extras.getInt("id"));
                i.putExtra("email", extras.getString("email"));
                i.putExtra("username", extras.getString("username"));
                i.putExtra("password", extras.getString("password"));
                startActivity(i);
            }

        });
    }

    /**
     * Get the results from the Database at the end of the game
     */
    public void getResults() {
        try {
            URL url = new URL("http://parallel.gg/rags-to-riches/get-results.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = "";
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

            String[] players = result.split("/");

            firstName.setText(players[0].split(",")[0]+"\nBalance: $ "+players[0].split(",")[1]);

            secondName.setText(players[1].split(",")[0]+"\nBalance: $ "+players[1].split(",")[1]);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
