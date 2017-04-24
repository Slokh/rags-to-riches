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
import com.example.alejandro.app1.models.Achievement;
import com.example.alejandro.app1.models.Company;
import com.example.alejandro.app1.models.Account;
import com.example.alejandro.app1.models.Portfolio;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

import static android.R.attr.path;
import static android.R.attr.port;
import static com.example.alejandro.app1.R.drawable.company;
import static com.example.alejandro.app1.R.id.imageView;
import static com.example.alejandro.app1.R.id.price;


public class AchievementAdapter extends GenericArrayAdapter<Achievement> {

    private List<Achievement> achievements;
    private Activity activity;
    private Account account;


    public AchievementAdapter(Activity activity, Context context, Account account,List<Achievement> achievements) {
        super(context, achievements);
        this.activity = activity;
        this.account = account;
    }

    @Override
    public void drawText(TextView textView, Achievement object) {

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
            vi = mInflater.inflate(R.layout.layout_listrow_achievements, null);
        TextView text = (TextView) vi.findViewById(R.id.achievement);
        TextView progress = (TextView) vi.findViewById(R.id.progress);

        final Achievement achievement = data.get(position);

        text.setText(achievement.getName());
        progress.setText(achievement.getProgress());

        return vi;
    }


    /*

    The function companyHistory(Company company) generates a company's stock trend graph
    when a stock is clicked in game view. The graph is generated using the graphview packages that
    are imported.


     */

}
