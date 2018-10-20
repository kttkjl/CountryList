package com.example.jacky.countrydetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;

public class CountryDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_details);
        // Grab country
        Country country = (Country)getIntent().getSerializableExtra("country");

        //Grab image url and image_view
        ImageView tv_country_detail_image = findViewById(R.id.country_detail_image);
        String url = country.getFlag();

        SvgLoader.pluck()
                .with(this)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(url, tv_country_detail_image);

        // View References
        TextView tv_country_detail_name = findViewById(R.id.country_detail_name);
        TextView tv_country_detail_borders = findViewById(R.id.country_detail_borders);


        // Set things for this view
        tv_country_detail_name.setText(country.getName());
        tv_country_detail_borders.setText(country.getBorders().toString());
//        tv_country_detail_image.setI
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        SvgLoader.pluck().close();
    }
}
