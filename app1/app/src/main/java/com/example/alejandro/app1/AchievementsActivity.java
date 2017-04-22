package com.example.alejandro.app1;

import android.os.Bundle;

import android.content.Intent;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by alejandro on 4/16/2017.
 */

public class AchievementsActivity extends MainMenuActivity {

    private Button mBackButton = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);


        mBackButton = (Button) findViewById(R.id.goMainMenuActivity);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(view.getContext(), MainMenuActivity.class);
                startActivity(a);
                // attemptLogin();
            }

        });




    }
}
