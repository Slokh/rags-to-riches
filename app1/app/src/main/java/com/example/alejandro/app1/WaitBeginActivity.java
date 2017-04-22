package com.example.alejandro.app1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

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

/**
 * Created by kp605 on 4/21/17.
 */

public class WaitBeginActivity extends MainMenuActivity {

    private ProgressBar spinner;
    public boolean waiting = true;

    /**
     * General initializer of Android Activity
     * @param savedInstanceState    saved Instance of previous activity
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_begin);


        final Bundle extras = getIntent().getExtras();
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);

        final Intent i = new Intent(this, GameActivity.class);
        i.putExtra("id", extras.getInt("id"));
        i.putExtra("email", extras.getString("email"));
        i.putExtra("username", extras.getString("username"));
        i.putExtra("password", extras.getString("password"));
        i.putExtra("code", extras.getString("code"));

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (waiting) {
                    try {
                        // Sleep for 200 milliseconds.
                        //Just to display the progress slowly
                        Thread.sleep(5000);
                        if (playerCount() == 1){
                            waiting = false;
                            startActivity(i);
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } // while loop
            } // run()
        }).start();

    }


    public int playerCount() {
        try {
            URL url = new URL("http://parallel.gg/rags-to-riches/player-count.php");
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
            return Integer.parseInt(result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
