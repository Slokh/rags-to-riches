package com.example.alejandro.app1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.example.alejandro.app1.MainMenuActivity;
import com.example.alejandro.app1.R;
import com.example.alejandro.app1.models.Company;
import com.example.alejandro.app1.models.Portfolio;

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
import java.util.Random;
import java.security.SecureRandom;
import android.widget.EditText;

/**
 * GameModeActivity class handles all functions in regards to navigating our GameMode screen
 */
public class GameModeActivity extends MainMenuActivity {

    EditText editText = null;

    private Button mCreateGame = null;
    private Button mJoinGame = null;
    private Button mBackButton = null;

    /**
     * General initializer of Android Activity
     * @param savedInstanceState    saved Instance of previous activity
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);
        final Bundle extras = getIntent().getExtras();

        mCreateGame = (Button) findViewById(R.id.createGame);
        mCreateGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final String code = generateCode(4);
                AlertDialog alertDialog = new AlertDialog.Builder(GameModeActivity.this).create();
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
                AlertDialog alertDialog = new AlertDialog.Builder(GameModeActivity.this).create();
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

        mBackButton = (Button) findViewById(R.id.backButton);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), MainMenuActivity.class);
                startActivity(i);
                // attemptLogin();
            }

        });


    }

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

