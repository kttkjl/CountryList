package com.example.jacky.countrydetails;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ContinentActivity extends AppCompatActivity {

    private String TAG = ContinentActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private String[] regions = {"Asia", "Europe", "Africa", "Oceania", "Americas", "Polar", "Others"};
    private ArrayList<Country> asia_countries;
    private ArrayList<Country> europe_countries;
    private ArrayList<Country> africa_countries;
    private ArrayList<Country> oceania_countries;
    private ArrayList<Country> americas_countries;
    private ArrayList<Country> polar_countries;
    private ArrayList<Country> other_regions;

    private ListView lv_regions;
    // URL to get contacts JSON
    private static String COUNTRIES_ALL_URL = "http://restcountries.eu/rest/v2/all?fields=name;capital;region;population;area;borders;flag;alpha3Code";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init array list of countries
        asia_countries= new ArrayList<Country>();
        africa_countries = new ArrayList<Country>();
        americas_countries = new ArrayList<Country>();
        oceania_countries = new ArrayList<Country>();
        europe_countries= new ArrayList<Country>();
        polar_countries= new ArrayList<Country>();
        other_regions = new ArrayList<Country>();

        lv_regions = findViewById(R.id.regions_list);

        // Async call here
        new GetCountries().execute();

        // Create listener to country list
        lv_regions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            // Adapter view just KNOWS which thing is in the category

            Intent intent = new Intent(ContinentActivity.this, CountryListActivity.class);
            // Depending on what was clicked, bring that out
            String currClicked = ((TextView) view.findViewById(R.id.region_name)).getText().toString();
            System.out.println("Clicked position: " + currClicked);
            switch (currClicked) {
                case "Asia":
                    intent.putExtra("countries", asia_countries);
                    break;
                case "Europe":
                    intent.putExtra("countries", europe_countries);
                    break;
                case "Africa":
                    intent.putExtra("countries", africa_countries);
                    break;
                case "Americas":
                    intent.putExtra("countries", americas_countries);
                    break;
                case "polar":
                    intent.putExtra("countries", polar_countries);
                    break;
                case "Oceania":
                    intent.putExtra("countries", oceania_countries);
                    break;
                default:
                    intent.putExtra("countries", other_regions);
                    break;
            }
            startActivity(intent);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    /**
     * Async task class to get json by making   HTTP call
     */
    private class GetCountries extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(ContinentActivity.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(COUNTRIES_ALL_URL);
            Log.e(TAG, "Response from url: " + jsonStr);
            String [] detail_checklist = {"flag", "name", "alpha3Code", "capital", "region", "population", "area", "borders"};
            if (jsonStr != null) {
                try {
                    JSONArray countryJsonArray = new JSONArray(jsonStr);

                    // looping through All Countries
                    for (int i = 0; i < countryJsonArray.length(); i++) {
                        JSONObject c = countryJsonArray.getJSONObject(i);

                        String flagUrl = c.getString("flag");
                        String name = c.getString("name");
                        String alpha3Code = c.getString("alpha3Code");
                        String capital = c.getString("capital");
                        String population = c.getString("population");

                        double area;
                        // Checking for area problems
                        if(c.has("area")){
                            area = c.getDouble("area");
                        } else {
                            area = 0.0;
                        }

                        String region;
                        // Checking for area problems
                        if(c.has("region")){
                            region = c.getString("region");
                        } else {
                            region = "others";
                        }

                        ArrayList<String> borders = new ArrayList<String>();
                        JSONArray tempBorder = c.getJSONArray("borders");
                        if (tempBorder != null) {
                            for (int j=0; j<tempBorder.length() ;j++){
                                borders.add(tempBorder.getString(j));
                            }
                        }

                        // tmp hash map for single country
                        Country country = new Country();
                        // adding each child node to HashMap key => value
                        country.setFlag(flagUrl);
                        country.setName(name);
                        country.setAlpha3Code(alpha3Code);
                        country.setCapital(capital);
                        country.setRegion(region);
                        country.setPopulation(Integer.parseInt(population));
                        country.setArea(area);
                        country.setBorders(borders);

                        // adding country to country list
                        switch (region) {
                            case "Asia":
                                asia_countries.add(country);
                                break;
                            case "Europe":
                                europe_countries.add(country);
                                break;
                            case "Africa":
                                africa_countries.add(country);
                                break;
                            case "Oceania":
                                oceania_countries.add(country);
                                break;
                            case "Americas":
                                americas_countries.add(country);
                                break;
                            case "Polar":
                                polar_countries.add(country);
                                break;
                            default:
                                other_regions.add(country);
                                break;
                        }
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                        "Couldn't get json from server. Check LogCat for possible errors!",
                        Toast.LENGTH_LONG)
                        .show();
                    }
                });

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            RegionAdapter region_adapter = new RegionAdapter(ContinentActivity.this, regions);

            // Attach the adapter to a ListView
            lv_regions.setAdapter(region_adapter);
        }
    }
}
