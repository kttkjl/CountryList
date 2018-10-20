package com.example.jacky.countrydetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;

public class CountryListActivity extends AppCompatActivity {

    private ListView lv_countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<Country> countries = (ArrayList<Country>)getIntent().getSerializableExtra("countries");
        // OK we got the country passed in here.
        System.out.println("country name: " + countries.get(0).getName());

        CountryListAdapter countries_adapter = new CountryListAdapter(CountryListActivity.this, countries);

        lv_countries = findViewById(R.id.countries_list_view);

        // Attach the adapter to a ListView
        lv_countries.setAdapter(countries_adapter);

    }


}
