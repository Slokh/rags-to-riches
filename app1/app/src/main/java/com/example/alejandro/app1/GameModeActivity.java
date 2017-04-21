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
import java.util.Random;
import java.security.SecureRandom;

/**
 * GameModeActivity class handles all functions in regards to navigating our GameMode screen
 */
public class GameModeActivity extends MainMenuActivity {

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
                String code = generateCode(4);
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
                                startActivity(i);
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

