package com.example.jacky.countrydetails;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CountryAdapter extends ArrayAdapter<Country> {
    Context _context;

    public CountryAdapter(Context context, ArrayList<Country> countries) {
        super(context, 0, countries);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;
        // Get the data item for this position
        Country country = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.country_details, parent, false);
        }

        TextView tvBorders = (TextView) convertView.findViewById(R.id.country_detail_borders);
        TextView tvCountryName = (TextView) convertView.findViewById(R.id.country_detail_name);

        // Populate the data into the template view using the data object
        tvBorders.setText(country.getBorders().toString());
        tvCountryName.setText(country.getName());

        ImageView imgOnePhoto = (ImageView) convertView.findViewById(R.id.country_detail_image);

        // Return the completed view to render on screen
        return convertView;
    }
}
