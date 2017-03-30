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
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

import static android.R.attr.path;
import static android.R.attr.port;


public class CompanyAdapter extends GenericArrayAdapter<Company> implements NumberPicker.OnValueChangeListener {

    int priceAt;
    private List<Company> companies;
    private Activity activity;
    private Account account;
    private Portfolio portfolio;


    public CompanyAdapter(Activity activity, Context context, Account account, Portfolio portfolio, List<Company> companies, int priceAt) {
        super(context, companies);
        this.activity = activity;
        this.account = account;
        this.portfolio = portfolio;
        this.priceAt = priceAt;
    }

    @Override
    public void drawText(TextView textView, Company object) {

    }



    /*

    The function getView(final int position, View convertView, ViewGroup parent) is responsible for
    organizing the buttons and the stock data on the chart in GameView. The prices and text are also
    generated.

    It listens to user commands such as when users press the buy and sell button and then updates the
    users balance accordingly if proper conditions are met.


     */


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
        final TextView balanceAmount = (TextView) vi.findViewById(R.id.balanceAmount);

        imageView.setBackgroundColor(Color.GRAY);

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
                        portfolio.updateBalance(company.getPrice(),np.getValue(),true);
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
                            portfolio.updateBalance(company.getPrice(),np.getValue(),false);
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


    /*

    The function companyHistory(Company company) generates a company's stock trend graph
    when a stock is clicked in game view. The graph is generated using the graphview packages that
    are imported.


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
