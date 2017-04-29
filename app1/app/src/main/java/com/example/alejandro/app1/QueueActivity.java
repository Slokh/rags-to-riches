package com.example.alejandro.app1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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
import java.net.URLEncoder;

/**
 * QueueActivity class handles all functions regarding waiting for a public game to be found.
 *
 * Created by Kartik on 4/24/2017.
 */

public class QueueActivity extends MainMenuActivity {

    boolean waiting = true;
    int id = 0;

    TextView text;
    private ProgressBar spinner;
    private Button mBackButton = null;

    /**
     * General initializer of Android Activity
     * @param savedInstanceState    saved Instance of previous activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_queue);

        final Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");

        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);

        text = (TextView) findViewById(R.id.queueText);

        final Intent i = new Intent(this, GameActivity.class);
        i.putExtra("id", extras.getInt("id"));
        i.putExtra("email", extras.getString("email"));
        i.putExtra("username", extras.getString("username"));
        i.putExtra("password", extras.getString("password"));
        i.putExtra("code", extras.getString("code"));


        mBackButton = (Button) findViewById(R.id.backButton);
        mBackButton.setOnClickListener(new View.OnClickListener() {
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

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (waiting) {
                    final int inQueue = inQueue();
                    if (inQueue == 4){
                        waiting = false;
                        startActivity(i);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            text.setText(inQueue + "/4 Players");

                        }
                    });
                }
            }
        }).start();

    }

    /**
     * Update the game info in the database with a new private game session
     * @param code  Code used by private game creator
     */
    public void updateGameInfo(String code) {
        try {
            URL url = new URL("http://parallel.gg/rags-to-riches/game-info.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("amount","UTF-8")+"="+URLEncoder.encode("10","UTF-8");
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
            System.out.println(result);
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
     * Create portfolio for the user in the database
     * @param id    id of the user's account
     */
    public void createPortfolio(int id) {
        try {
            URL url = new URL("http://parallel.gg/rags-to-riches/create-portfolio.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode("" + id,"UTF-8");
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
     * Get the amount of people currently in queue
     * @return  amunt of people in queue
     */
    public int inQueue() {
        try {
            URL url = new URL("http://parallel.gg/rags-to-riches/in-queue.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode("" + id,"UTF-8");
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


