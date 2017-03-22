package com.example.alejandro.app1;

import android.os.Bundle;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

//package com.example.alejandro.app1;





/**
 * Created by alejandro on 3/17/2017.
 */

public class MainMenuActivity extends RegisterActivity {

    private ImageButton mSetting = null;
    private ImageButton mGame = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        mGame = (ImageButton) findViewById(R.id.goGameAcitivy);
        mGame.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(view.getContext(), GameActivity.class);
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
