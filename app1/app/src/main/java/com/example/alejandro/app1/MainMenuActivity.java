package com.example.alejandro.app1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.view.MenuItem;

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
import java.security.SecureRandom;
import java.util.Random;
import android.support.v7.widget.Toolbar;

/**
 * Written by: Kartik Patel, Alejandro Aguilar, Deep Patel
 * Tested/Debugged by: Kartik Patel, Deep Patel
 *
 * MainMenuActivity class handles all functions regarding to navigating our Main Menu screen,
 * mainly finding games or checking Achievements.
 */
public class MainMenuActivity extends AppCompatActivity {

    EditText editText = null;
    Toolbar mActionBarToolbar;
    private Button mCreateGame = null;
    private Button mJoinGame = null;
    private Button mFindGame = null;

    /**
     * General initializer of Android Activity
     * @param savedInstanceState    saved Instance of previous activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mainmenu);

        final Bundle extras = getIntent().getExtras();

        grabAchievements(extras.getInt("id"));

        mActionBarToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setSubtitle("Enter A Game");
        mActionBarToolbar.setTitleTextColor(Color.WHITE);
        mActionBarToolbar.setSubtitleTextColor(Color.WHITE);

        mFindGame = (Button) findViewById(R.id.findPublicGame);
        mFindGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
            Intent i = new Intent(view.getContext(), QueueActivity.class);
            i.putExtra("id", extras.getInt("id"));
            i.putExtra("email", extras.getString("email"));
            i.putExtra("username", extras.getString("username"));
            i.putExtra("password", extras.getString("password"));
            createPortfolio(extras.getInt("id"));
            startActivity(i);

            }

        });

        mCreateGame = (Button) findViewById(R.id.createGame);
        mCreateGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
            final String code = generateCode(4);
            AlertDialog alertDialog = new AlertDialog.Builder(MainMenuActivity.this).create();
            alertDialog.setTitle("Invite Friends");
            alertDialog.setMessage("You're game code is: " + code);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(view.getContext(), WaitBeginActivity.class);
                        i.putExtra("id", extras.getInt("id"));
                        i.putExtra("email", extras.getString("email"));
                        i.putExtra("username", extras.getString("username"));
                        i.putExtra("password", extras.getString("password"));
                        i.putExtra("code", code);
                        updateGameInfo(code);
                        createPortfolio(extras.getInt("id"));
                        startActivity(i);
                        dialog.dismiss();
                    }
                });
            alertDialog.show();
            }

        });

        mJoinGame = (Button) findViewById(R.id.joinGame);
        mJoinGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
            editText = new EditText(view.getContext());
            AlertDialog alertDialog = new AlertDialog.Builder(MainMenuActivity.this).create();
            alertDialog.setTitle("Enter Code");
            alertDialog.setMessage("Enter the game code: ");
            alertDialog.setView(editText);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String code = editText.getText().toString();
                        if(checkCode(code)) {
                            Intent i = new Intent(view.getContext(), WaitBeginActivity.class);
                            i.putExtra("id", extras.getInt("id"));
                            i.putExtra("email", extras.getString("email"));
                            i.putExtra("username", extras.getString("username"));
                            i.putExtra("password", extras.getString("password"));
                            i.putExtra("code", code);
                            createPortfolio(extras.getInt("id"));
                            startActivity(i);
                        }
                        dialog.dismiss();
                    }
                });
            alertDialog.show();
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;

    }

    /**
     * Handles user interaction on options menu
     * @param item  Item selected by user
     * @return      true on success
     */
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.action_settings:
                Intent k = new Intent(MainMenuActivity.this, SettingsActivity.class);
                startActivity(k);
                return true;

            case R.id.action_achievements:
                final Bundle extras = getIntent().getExtras();
                Intent l = new Intent(MainMenuActivity.this, AchievementsActivity.class);
                l.putExtra("id", extras.getInt("id"));
                l.putExtra("email", extras.getString("email"));
                l.putExtra("username", extras.getString("username"));
                l.putExtra("password", extras.getString("password"));
                startActivity(l);
                return true;

            default:
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Check if user entered the right code for the private game
     * @param code  Code entered by user
     * @return      true on success, false on failure
     */
    public boolean checkCode(String code) {
        try {
            URL url = new URL("http://parallel.gg/rags-to-riches/check-code.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("code","UTF-8")+"="+URLEncoder.encode("" + code,"UTF-8");
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
            System.out.println(result);
            if(result.trim().equals("TRUE")) return true;
            return false;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
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
     * Grab the achievements for a particular user
     * @param id    id of the user's account
     */
    public void grabAchievements(int id) {
        try {
            URL url = new URL("http://parallel.gg/rags-to-riches/grab-achievements.php");
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
     * Generate a random character alphanumeric code to be used as an invite code
     * @param length    length of code
     * @return          String containing the code
     */
    public static String generateCode(int length) {
        char[]characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Random random = new SecureRandom();
        char[] result = new char[length];
        for (int i = 0; i < result.length; i++) {
            int randomCharIndex = random.nextInt(characterSet.length);
            result[i] = characterSet[randomCharIndex];
        }
        return new String(result);
    }

}