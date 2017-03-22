package com.example.alejandro.app1;

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

public class GameModeActivity extends MainMenuActivity {

    private Button mGamePublic = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);

        mGamePublic = (Button) findViewById(R.id.goGameActivity);
        mGamePublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), GameActivity.class);
                startActivity(i);
                // attemptLogin();
            }

        });


    }

}