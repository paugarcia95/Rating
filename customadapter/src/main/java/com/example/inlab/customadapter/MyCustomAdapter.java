package com.example.inlab.customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.inlab.customadapter.model.Restaurants;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Created by inlab on 27/01/2015.
 */

public class MyCustomAdapter extends ArrayAdapter {

    private List<Restaurants> mRestaurants;
    private Context mContext;
    private int mResource;

    public MyCustomAdapter(Context context, List<Restaurants> data) {
        super(context, R.layout.custom_item, data);
        mContext = context;
        mRestaurants = data;
        mResource = R.layout.custom_item;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(mResource, parent, false);

        TextView name = (TextView) rowView.findViewById(R.id.name);
        TextView rate = (TextView) rowView.findViewById(R.id.rate);

        Restaurants restaurant = mRestaurants.get(position);
        name.setText(restaurant.name);
        rate.setText(restaurant.rate);


        return rowView;
    }
}
