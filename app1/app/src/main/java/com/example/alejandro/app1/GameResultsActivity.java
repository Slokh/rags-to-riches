package com.example.alejandro.app1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * GameResultsActivity class handles all functions in regards to the end game scenario
 */
public class GameResultsActivity extends AppCompatActivity {


    private Button mQuitButton = null;
    EditText standings;

    /**
     * General initializer of Android Activity
     * @param savedInstanceState    saved Instance of previous activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuitButton = (Button) findViewById(R.id.goMainMenuActivity);
        mQuitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),MainMenuActivity.class);
                startActivity(i);
            }
        });

        double firstplayer = (Math. random() * 9999 + 1000);
        double secondplayer = (Math. random() * 9999 + 1000);
        double thirdplayer = (Math. random() * 9999 + 1000);

        standings.setText("You: 5000\n1st Player: " + firstplayer + "\n2nd Player: " + secondplayer + "\n3rd Player: " + thirdplayer);




    }

}
