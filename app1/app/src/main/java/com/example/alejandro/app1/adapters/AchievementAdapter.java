package com.example.alejandro.app1.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alejandro.app1.R;
import com.example.alejandro.app1.models.Achievement;
import com.example.alejandro.app1.models.Account;

import java.util.List;

/**
 * Adapts list of achievements into ListView
 * Created by Kartik on 4/22/2017.
 */
public class AchievementAdapter extends GenericArrayAdapter<Achievement> {

    private Activity activity;
    private Account account;

    /**
     *
     * @param activity      app activity
     * @param context       app context
     * @param account       user's account
     * @param achievements  list of achievements
     */
    public AchievementAdapter(Activity activity, Context context, Account account,List<Achievement> achievements) {
        super(context, achievements);
        this.activity = activity;
        this.account = account;
    }

    @Override
    public void drawText(TextView textView, Achievement object) { }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View vi = convertView;

        if (vi == null) vi = mInflater.inflate(R.layout.layout_listrow_achievements, null);

        TextView text = (TextView) vi.findViewById(R.id.achievement);
        TextView progress = (TextView) vi.findViewById(R.id.progress);

        final Achievement achievement = data.get(position);

        text.setText(achievement.getName());
        progress.setText(achievement.getProgress());

        return vi;
    }

}
