package com.example.alejandro.app1.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.alejandro.app1.R;
import com.example.alejandro.app1.models.Company;
import com.example.alejandro.app1.models.Account;
import com.example.alejandro.app1.models.Portfolio;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;


/**
 * Written by: Kartik Patel
 * Tested/Debugged by: Kartik Patel
 *
 * Adapts list of companies into ListView
 */
public class CompanyAdapter extends GenericArrayAdapter<Company> implements NumberPicker.OnValueChangeListener {

    int priceAt;
    private Activity activity;
    private Portfolio portfolio;

    /**
     *
     * @param activity  app activity
     * @param context   app context
     * @param account   user's account
     * @param portfolio user's portfolio
     * @param companies list of companies
     * @param priceAt   current turn in the game
     */
    public CompanyAdapter(Activity activity, Context context, Account account, Portfolio portfolio, List<Company> companies, int priceAt) {
        super(context, companies);
        this.activity = activity;
        this.portfolio = portfolio;
        this.priceAt = priceAt;
    }

    @Override
    public void drawText(TextView textView, Company object) { }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View vi = convertView;

        if (vi == null) vi = mInflater.inflate(R.layout.layout_listrow, null);

        TextView text = (TextView) vi.findViewById(R.id.companyText);
        TextView price = (TextView) vi.findViewById(R.id.companyPrice);
        Button buyButton = (Button) vi.findViewById(R.id.companyBuyButton);
        Button sellButton = (Button) vi.findViewById(R.id.companySellButton);

        ImageView imageView = (ImageView) vi.findViewById(R.id.companyImage);
        imageView.setImageResource(R.drawable.company);
        imageView.setBackgroundColor(Color.WHITE);

        final Company company = data.get(position);

        text.setText(company.getName());
        price.setText(company.getPriceText());

        text.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                companyHistory(company);
            }
        });
        price.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                companyHistory(company);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                companyHistory(company);
            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            final Dialog dialogPicker = new Dialog(activity);

            dialogPicker.setTitle("NumberPicker");
            dialogPicker.setContentView(R.layout.buy_dialog);

            Button buy = (Button) dialogPicker.findViewById(R.id.buy);
            Button cancel = (Button) dialogPicker.findViewById(R.id.cancel);

            final NumberPicker np = (NumberPicker) dialogPicker.findViewById(R.id.numberPicker1);
            np.setMaxValue(100);
            np.setMinValue(0);
            np.setWrapSelectorWheel(false);

            buy.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(final View v) {

                dialogPicker.dismiss();
                portfolio.updateStock(company, portfolio.getAmountOfStock(company) + np.getValue());
                portfolio.updateBalance(company.getPrice(), np.getValue(), true);

                AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
                alertDialog.setTitle("Success!");
                alertDialog.setMessage("Bought " + String.valueOf(np.getValue()) + " stocks!\nBalance Updated: " + portfolio.getBalanceText());

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

            Button sell = (Button) dialogPicker.findViewById(R.id.sell);
            Button cancel = (Button) dialogPicker.findViewById(R.id.cancel);

            final NumberPicker np = (NumberPicker) dialogPicker.findViewById(R.id.numberPicker1);
            np.setMaxValue(100); // max value 100
            np.setMinValue(0);   // min value 0
            np.setWrapSelectorWheel(false);

            sell.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(final View v) {

                dialogPicker.dismiss();

                if(portfolio.getAmountOfStock(company)-np.getValue() >= 0) {

                    portfolio.updateStock(company, portfolio.getAmountOfStock(company) - np.getValue());
                    portfolio.updateBalance(company.getPrice(),np.getValue(),false);

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

    /**
     * Displays a graph of the stock trends for a given company
     * @param company
     */
    public void companyHistory(Company company) {

        final Dialog infoDidalog = new Dialog(activity);
        infoDidalog.setTitle("Stock Info");
        infoDidalog.setContentView(R.layout.stock_info);
        infoDidalog.show();

        GraphView graph = (GraphView) infoDidalog.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
            new DataPoint(1, company.getPriceAt(company.returnWeek()-9)),
            new DataPoint(2, company.getPriceAt(company.returnWeek()-8)),
            new DataPoint(3, company.getPriceAt(company.returnWeek()-7)),
            new DataPoint(4, company.getPriceAt(company.returnWeek()-6)),
            new DataPoint(5, company.getPriceAt(company.returnWeek()-5)),
            new DataPoint(6, company.getPriceAt(company.returnWeek()-4)),
            new DataPoint(7, company.getPriceAt(company.returnWeek()-3)),
            new DataPoint(8, company.getPriceAt(company.returnWeek()-2)),
            new DataPoint(9, company.getPriceAt(company.returnWeek()-1)),
        });

        graph.setTitle(company.getName() + " Trends");
        graph.addSeries(series);
        graph.getViewport().setMinX(1);

        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);

        graph.getViewport().setScalableY(true);
        graph.getViewport().setScrollableY(true);

        graph.getGridLabelRenderer().setNumHorizontalLabels(9);
        graph.getViewport().setXAxisBoundsManual(true);

        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(9);
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        Log.i("value is",""+newVal);
    }
}
