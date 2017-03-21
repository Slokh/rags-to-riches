package com.example.alejandro.app1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Kartik on 3/20/2017.
 */

public class GameActivity extends LoginActivity{

    private Button mQuitButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mQuitButton = (Button) findViewById(R.id.quitButton);
        mQuitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),RegisterActivity.class);
                startActivity(i);
                // attemptLogin();
            }
        });
    }
}
