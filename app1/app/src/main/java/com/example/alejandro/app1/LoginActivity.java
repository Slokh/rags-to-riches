package com.example.alejandro.app1;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.alejandro.app1.models.Account;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


/**
 * LoginActivity class handles all functions regarding logging a user into our system
 *
 * Created by alejandro on 3/17/2017.
 */
public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private Button mRegisterButton = null;
    private Button mLoginButton = null;

    /**
     * General initializer of Android Activity
     * @param savedInstanceState    saved Instance of previous activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        mLoginButton = (Button) findViewById(R.id.goLoginActivity);
        mLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
            if(attemptLogin()) {
                Account account = validateLogin();
                if(account != null) {
                    Intent i = new Intent(view.getContext(), IntroActivity.class);
                    i.putExtra("id", account.getId());
                    i.putExtra("email", account.getEmail());
                    i.putExtra("username", account.getUsername());
                    i.putExtra("password", account.getPassword());
                    startActivity(i);
                }
            }
            }
        });

        mRegisterButton = (Button) findViewById(R.id.goRegisterActivity);
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i = new Intent(view.getContext(), RegisterActivity.class);
            startActivity(i);
            }
        });
    }

    /**
     * Validates the user's information based on our account constraints
     * @return whether the user entered the correct information to login
     */
    private boolean attemptLogin() {

        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        View focusView = null;

        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            focusView.requestFocus();
            return false;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            focusView.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            focusView.requestFocus();
            return false;
        } else if(!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            focusView.requestFocus();
            return false;
        }

        return true;
    }

    /**
     * Validates the user's account with our current database of users
     * @return whether the account was found in our database and the passwords match
     */
    private Account validateLogin() {

        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        View focusView = null;

        try {
            URL url = new URL("http://parallel.gg/rags-to-riches/login-account.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode("" + email,"UTF-8")+"&";
            post_data += URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode("" + password,"UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result="";
            String line="";
            while((line = bufferedReader.readLine())!= null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            if(result.equals("FALSE")) {
                mEmailView.setError(getString(R.string.error_invalid_user_pass));
                mPasswordView.setError(getString(R.string.error_invalid_user_pass));
                focusView = mEmailView;
                focusView = mPasswordView;
                focusView.requestFocus();
                return null;
            } else {
                String[] split = result.split("/");
                return new Account(Integer.parseInt(split[0]), split[1], split[2], split[3]);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Checks if the user actually entered an email
     * @param email the user's entered email
     * @return  true if valid
     */
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }
    /**
     * Checks if the user entered a long enough password
     * @param password  the user's entered password
     * @return  true if valid
     */
    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}