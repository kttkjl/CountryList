package com.example.jacky.countrydetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class CountryListActivity extends AppCompatActivity {

    private ArrayList<Country> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countries_list);

        countries = (ArrayList<Country>)getIntent().getSerializableExtra("countries");

        ListView lv_countries = findViewById(R.id.countries_list);

        CountryListAdapter countries_adapter = new CountryListAdapter(CountryListActivity.this, countries);

        // Attach the adapter to a ListView
        lv_countries.setAdapter(countries_adapter);

        lv_countries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Adapter view just KNOWS which thing is in the category

                Intent intent = new Intent(CountryListActivity.this, CountryDetailActivity.class);
                // Depending on what was clicked, bring that out
                intent.putExtra("country", countries.get(i));
                startActivity(intent);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
