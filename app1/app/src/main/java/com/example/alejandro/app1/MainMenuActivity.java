package com.example.alejandro.app1;

import android.os.Bundle;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.view.MenuItem;

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

}
