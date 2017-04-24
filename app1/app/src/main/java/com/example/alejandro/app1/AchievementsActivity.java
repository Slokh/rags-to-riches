package com.example.alejandro.app1;

import android.content.Context;
import android.os.Bundle;

import android.content.Intent;

import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.alejandro.app1.adapters.AchievementAdapter;
import com.example.alejandro.app1.adapters.CompanyAdapter;
import com.example.alejandro.app1.models.Account;
import com.example.alejandro.app1.models.Achievement;
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
 * Created by alejandro on 4/16/2017.
 */

public class AchievementsActivity extends MainMenuActivity {

    private Button mBackButton = null;
    Account account;
    List<Achievement> achievements;

    String[] achievementsArray = {"Win one game", "Win five games", "Win ten games", "Earn $100", "Earn $1000", "Earn $10000"};

    ArrayAdapter<Achievement> displayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
        final Bundle extras = getIntent().getExtras();
        account = new Account(extras.getInt("id"), extras.getString("email"), extras.getString("username"), extras.getString("password"));

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String[] achievementResults = grabAchievements();

        int wins = Integer.parseInt(achievementResults[0]);
        int earnings = Integer.parseInt(achievementResults[1]);

        achievements = new ArrayList<Achievement>();
        achievements.add(new Achievement(achievementsArray[0], wins >= 1 ? "Completed" : wins + "/1"));
        achievements.add(new Achievement(achievementsArray[1], wins >= 5 ? "Completed" : wins + "/5"));
        achievements.add(new Achievement(achievementsArray[2], wins >= 10 ? "Completed" : wins + "/10"));
        achievements.add(new Achievement(achievementsArray[3], earnings >= 100 ? "Completed" : earnings + "/100"));
        achievements.add(new Achievement(achievementsArray[4], earnings >= 1000 ? "Completed" : earnings + "/1000"));
        achievements.add(new Achievement(achievementsArray[5], earnings >= 1000 ? "Completed" : earnings + "/10000"));

        final Context context = getApplicationContext();
        displayAdapter = new AchievementAdapter(AchievementsActivity.this, context, account, achievements);

        ListView listView = (ListView) findViewById(R.id.achievementList);
        listView.setAdapter(displayAdapter);

        mBackButton = (Button) findViewById(R.id.goMainMenuActivity);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), MainMenuActivity.class);
                i.putExtra("id", extras.getInt("id"));
                i.putExtra("email", extras.getString("email"));
                i.putExtra("username", extras.getString("username"));
                i.putExtra("password", extras.getString("password"));
                startActivity(i);
                // attemptLogin();
            }

        });




    }
    public String[] grabAchievements() {
        try {
            URL url = new URL("http://parallel.gg/rags-to-riches/grab-achievements.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode("" + account.getId(),"UTF-8");
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
            return result.split("/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {
        return false;
    }
}
