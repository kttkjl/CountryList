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

public class RegionAdapter extends ArrayAdapter<String> {
    Context _context;

    public RegionAdapter(Context context, String[] regions) {
        super(context, 0, regions);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;
        // Get the data item for this position
        String region = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.regions_list, parent, false);
        }

        TextView tv_region = (TextView) convertView.findViewById(R.id.region_name);
        tv_region.setText(region);
        // Return the completed view to render on screen
        return convertView;
    }
}

