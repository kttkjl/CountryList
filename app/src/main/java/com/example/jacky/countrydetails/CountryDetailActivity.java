package com.example.jacky.countrydetails;

import android.os.Bundle;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

public class CountryDetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_details);
        String food_category = getIntent().getStringExtra("food_category");
        int foodIndex = (Integer) getIntent().getExtras().get("food_category_index");
    }
}
