package com.example.alejandro.app1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
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
import android.support.v7.app.AppCompatActivity;
import static android.os.Build.VERSION_CODES.M;

//package com.example.alejandro.app1;





/**
 * Created by alejandro on 3/17/2017.
 */

/**
 * MainMenuActivity class handles all functions in regards to navigating our main menu screen
 */
public class MainMenuActivity extends AppCompatActivity {

    private ImageButton mSetting = null;
    private ImageButton mGameMode = null;
    EditText editText = null;
    Toolbar mActionBarToolbar;
    private Button mCreateGame = null;
    private Button mJoinGame = null;

    /**
     * General initializer of Android Activity
     * @param savedInstanceState    saved Instance of previous activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {


      //  setActivityBackgroundColor(79B144);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mainmenu);
        final Bundle extras = getIntent().getExtras();



        mActionBarToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setSubtitle("Enter A Game");
        mActionBarToolbar.setTitleTextColor(Color.WHITE);
        mActionBarToolbar.setSubtitleTextColor(Color.WHITE);




        // final Bundle extras = getIntent().getExtras();

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











        /*
        mGameMode = (ImageButton) findViewById(R.id.goGameModeActivity);
        mGameMode.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(view.getContext(), MainMenuActivity.class);
                a.putExtra("id", extras.getInt("id"));
                a.putExtra("email", extras.getString("email"));
                a.putExtra("username", extras.getString("username"));
                a.putExtra("password", extras.getString("password"));
                startActivity(a);
                // attemptLogin();
            }

        });
        */
        //new buttons part


       // private Button mBackButton = null;

























/*
        mSetting = (ImageButton) findViewById(R.id.goSettingsActivity);
        mSetting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), SettingsActivity.class);
                startActivity(i);
                // attemptLogin();
                }

        });
*/
    }


    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;

    }

//    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_settings:

                Intent k = new Intent(MainMenuActivity.this, SettingsActivity.class);
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




















    // all private game stuff



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
            if(result.trim().equals("TRUE")) {
                return true;
            }
            return false;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


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


