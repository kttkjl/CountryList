package com.example.jacky.countrydetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CountryListActivity extends AppCompatActivity {

    private ListView lv_countries;
    private ArrayList<Country> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        countries = (ArrayList<Country>)getIntent().getSerializableExtra("countries");
        // OK we got the country passed in here.
        System.out.println("country name: " + countries.get(0).getName());

        CountryListAdapter countries_adapter = new CountryListAdapter(CountryListActivity.this, countries);

        lv_countries = findViewById(R.id.countries_list_view);

        // Attach the adapter to a ListView
        lv_countries.setAdapter(countries_adapter);


        lv_countries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Adapter view just KNOWS which thing is in the category

                Intent intent = new Intent(CountryListActivity.this, CountryDetailActivity.class);
                // Depending on what was clicked, bring that out
//                String currClicked = ((TextView) view.findViewById(R.id.region_name)).getText().toString();
//                System.out.println("Clicked position: " + currClicked);
                intent.putExtra("country", countries.get(i));
                startActivity(intent);
            }
        });

    }
}
