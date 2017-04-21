package com.example.alejandro.app1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * Created by kp605 on 4/21/17.
 */

public class WaitEndActivity extends MainMenuActivity {

    private ProgressBar spinner;
    /**
     * General initializer of Android Activity
     * @param savedInstanceState    saved Instance of previous activity
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_end);


        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);


    }

}
