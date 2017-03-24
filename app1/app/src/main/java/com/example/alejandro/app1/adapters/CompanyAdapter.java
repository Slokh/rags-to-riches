package com.example.alejandro.app1.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.alejandro.app1.GameActivity;
import com.example.alejandro.app1.MainMenuActivity;
import com.example.alejandro.app1.R;
import com.example.alejandro.app1.models.Company;
import com.example.alejandro.app1.models.Account;
import com.example.alejandro.app1.models.Portfolio;

import java.util.List;

import static android.R.attr.path;
import static android.R.attr.port;

/**
 * Created by Kartik on 3/20/2017.
 */

public class CompanyAdapter extends GenericArrayAdapter<Company> implements NumberPicker.OnValueChangeListener {

    private List<Company> companies;
    private Activity activity;
    private Account account;
    private Portfolio portfolio;

    public CompanyAdapter(Activity activity, Context context, Account account, Portfolio portfolio, List<Company> companies) {
        super(context, companies);
        this.activity = activity;
        this.account = account;
        this.portfolio = portfolio;
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
        ImageView imageView = (ImageView) vi.findViewById(R.id.companyImage);
        imageView.setBackgroundColor(Color.GRAY);

        final Company company = data.get(position);

        text.setText(company.getName());
        price.setText(company.getPrice() + "");


        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialogPicker = new Dialog(activity);
                dialogPicker.setTitle("NumberPicker");
                dialogPicker.setContentView(R.layout.buy_dialog);
                Button buy = (Button) dialogPicker.findViewById(R.id.buy);
                Button cancel = (Button) dialogPicker.findViewById(R.id.cancel);
                final NumberPicker np = (NumberPicker) dialogPicker.findViewById(R.id.numberPicker1);
                np.setMaxValue(100); // max value 100
                np.setMinValue(0);   // min value 0
                np.setWrapSelectorWheel(false);
                buy.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(final View v) {
                        dialogPicker.dismiss();
                        portfolio.updateStock(company,portfolio.getAmountOfStock(company)+np.getValue());
                        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
                        alertDialog.setTitle("Success!");
                        alertDialog.setMessage("Bought " + String.valueOf(np.getValue()) + " stocks!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogSuccess, int which) {
                                        dialogSuccess.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        dialogPicker.dismiss(); // dismiss the dialog
                    }
                });
                dialogPicker.show();
            }
        });

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialogPicker = new Dialog(activity);
                dialogPicker.setTitle("NumberPicker");
                dialogPicker.setContentView(R.layout.sell_dialog);
                Button buy = (Button) dialogPicker.findViewById(R.id.sell);
                Button cancel = (Button) dialogPicker.findViewById(R.id.cancel);
                final NumberPicker np = (NumberPicker) dialogPicker.findViewById(R.id.numberPicker1);
                np.setMaxValue(100); // max value 100
                np.setMinValue(0);   // min value 0
                np.setWrapSelectorWheel(false);
                buy.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(final View v) {
                        dialogPicker.dismiss();
                        if(portfolio.getAmountOfStock(company)-np.getValue() >= 0) {
                            portfolio.updateStock(company, portfolio.getAmountOfStock(company) - np.getValue());
                            AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
                            alertDialog.setTitle("Success!");
                            alertDialog.setMessage("Sold " + String.valueOf(np.getValue()) + " stocks!");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialogSuccess, int which) {
                                            dialogSuccess.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        } else {
                            AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
                            alertDialog.setTitle("Error!");
                            alertDialog.setMessage("Not enough stocks to sell!");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialogSuccess, int which) {
                                            dialogSuccess.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        }
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        dialogPicker.dismiss(); // dismiss the dialog
                    }
                });
                dialogPicker.show();
            }
        });

        return vi;
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

        Log.i("value is",""+newVal);

    }
}
