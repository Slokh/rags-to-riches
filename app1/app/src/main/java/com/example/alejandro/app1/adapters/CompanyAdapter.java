package com.example.alejandro.app1.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alejandro.app1.R;
import com.example.alejandro.app1.models.Company;

import java.util.List;

import static android.R.attr.path;

/**
 * Created by Kartik on 3/20/2017.
 */

public class CompanyAdapter extends GenericArrayAdapter<Company> {

    private List<Company> companies;
    private Activity activity;

    public CompanyAdapter(Activity activity, Context context, List<Company> companies) {
        super(context, companies);
        this.activity = activity;
    }

    @Override
    public void drawText(TextView textView, Company object) {

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        if (vi == null)
            vi = mInflater.inflate(R.layout.layout_listrow, null);
        TextView text = (TextView) vi.findViewById(R.id.companyText);
        TextView price = (TextView) vi.findViewById(R.id.companyPrice);
        Button buyButton = (Button) vi.findViewById(R.id.companyBuyButton);
        Button sellButton = (Button) vi.findViewById(R.id.companySellButton);

        text.setText(data.get(position).getName());
        price.setText(data.get(position).getPrice() + "");

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return vi;
    }
}
