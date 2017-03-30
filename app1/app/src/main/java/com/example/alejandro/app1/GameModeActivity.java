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

/**
 * GameModeActivity class handles all functions in regards to navigating our GameMode screen
 */
public class GameModeActivity extends MainMenuActivity {

    private Button mGamePublic = null;
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

        mGamePublic = (Button) findViewById(R.id.goGameActivity);
        mGamePublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), GameActivity.class);
                i.putExtra("id", extras.getInt("id"));
                i.putExtra("email", extras.getString("email"));
                i.putExtra("username", extras.getString("username"));
                i.putExtra("password", extras.getString("password"));
                startActivity(i);
                // attemptLogin();
            }

        });

        mGamePublic = (Button) findViewById(R.id.backButton);
        mGamePublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), MainMenuActivity.class);
                startActivity(i);
                // attemptLogin();
            }

        });


    }

}
