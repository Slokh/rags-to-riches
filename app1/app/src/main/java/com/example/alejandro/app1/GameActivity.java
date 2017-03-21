package com.example.alejandro.app1;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.alejandro.app1.adapters.CompanyAdapter;
import com.example.alejandro.app1.models.Company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kartik on 3/20/2017.
 */

public class GameActivity extends AppCompatActivity {

    ArrayAdapter<Company> displayAdapter;
    private Button mQuitButton = null;
    List<Company> companies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        companies = new ArrayList<Company>();
        companies.add(new Company("Company A", "A", "A company.", "Apple", 50));
        companies.add(new Company("Company B", "B", "B company.", "Apple", 50));
        companies.add(new Company("Company C", "C", "C company.", "Apple", 50));
        companies.add(new Company("Company D", "D", "D company.", "Apple", 50));
        companies.add(new Company("Company E", "E", "E company.", "Apple", 50));

        Context context = getApplicationContext();
        displayAdapter = new CompanyAdapter(GameActivity.this, context, companies);

        ListView listView = (ListView) findViewById(R.id.companyList);
        listView.setAdapter(displayAdapter);

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
