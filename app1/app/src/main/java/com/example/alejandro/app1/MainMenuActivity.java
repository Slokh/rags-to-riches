package com.example.alejandro.app1;

import android.os.Bundle;

import android.content.Intent;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

//package com.example.alejandro.app1;





/**
 * Created by alejandro on 3/17/2017.
 */

public class MainMenuActivity extends RegisterActivity {

    private ImageButton mSetting = null;
    private ImageButton mGameMode = null;

    @Override

    /*

    the function onCreate() in the MainMenuActivity that generates the main homescreen.

     */


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        final Bundle extras = getIntent().getExtras();

        mGameMode = (ImageButton) findViewById(R.id.goGameModeActivity);
        mGameMode.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(view.getContext(), GameModeActivity.class);
                a.putExtra("id", extras.getInt("id"));
                a.putExtra("email", extras.getString("email"));
                a.putExtra("username", extras.getString("username"));
                a.putExtra("password", extras.getString("password"));
                startActivity(a);
                // attemptLogin();
            }

        });

        mSetting = (ImageButton) findViewById(R.id.goSettingsActivity);
        mSetting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), SettingsActivity.class);
                startActivity(i);
                // attemptLogin();
                }

        });

    }


}
