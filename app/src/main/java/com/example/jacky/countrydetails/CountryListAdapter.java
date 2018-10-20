package com.example.jacky.countrydetails;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import android.widget.ArrayAdapter;

public class CountryListAdapter extends ArrayAdapter<Country> {
    Context _context;

    public CountryListAdapter(Context context, ArrayList<Country> countries) {
        super(context, 0, countries);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;
        // Get the data item for this position
        Country country = getItem(position);
//        System.out.println("==============================CURRENT REGION : " + region);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.countries_list, parent, false);
        }

        TextView tv_country_name = (TextView) convertView.findViewById(R.id.country_name);
        tv_country_name.setText(country.getName());

        return convertView;
    }
}
