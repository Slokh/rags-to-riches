package com.example.alejandro.app1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Superclass for AchievementAdapter and Company Adapter
 * Created by Kartik on 3/20/2017.
 */
public abstract class GenericArrayAdapter<T> extends ArrayAdapter<T> {

    protected LayoutInflater mInflater;
    protected List<T> data;

    /**
     *
     * @param context   app context
     * @param objects   list of objects
     */
    public GenericArrayAdapter(Context context, List<T> objects) {
        super(context, 0, objects);
        init(context);
        this.data = objects;
    }

    /**
     * Initialize inflater from context
     * @param context   app context
     */
    private void init(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    /**
     * Draw Text
     * @param textView  the text view
     * @param object    object to draw
     */
    public abstract void drawText(TextView textView, T object);

    /**
     * Get the view for a specific ListView row
     * @param position      position on ListView
     * @param convertView   view to convert to
     * @param parent        parent view
     * @return              new view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder vh;

        if (convertView == null) {
            convertView = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        drawText(vh.textView, getItem(position));

        return convertView;
    }

    /**
     * Holds the new ListView row view
     */
    static class ViewHolder {

        TextView textView;

        private ViewHolder(View rootView) {
            textView = (TextView) rootView.findViewById(android.R.id.text1);
        }

    }
}