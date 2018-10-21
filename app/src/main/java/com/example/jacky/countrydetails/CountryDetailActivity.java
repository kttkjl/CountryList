package com.example.jacky.countrydetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.ahmadrosid.svgloader.SvgLoader;

public class CountryDetailActivity extends AppCompatActivity {
    private ShareActionProvider shareActionProvider;
    private Country country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_details);
        // Grab country
        country = (Country)getIntent().getSerializableExtra("country");

        //Grab image url and image_view
        ImageView tv_country_detail_image = findViewById(R.id.country_detail_image);
        String url = country.getFlag();

        SvgLoader.pluck()
                .with(this)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(url, tv_country_detail_image);

        // View References
        TextView tv_country_detail_name = findViewById(R.id.country_detail_name);
        TextView tv_country_detail_capital = findViewById(R.id.country_detail_capital);
        TextView tv_country_detail_region = findViewById(R.id.country_detail_region);
        TextView tv_country_detail_population = findViewById(R.id.country_detail_population);
        TextView tv_country_detail_area = findViewById(R.id.country_detail_area);
        TextView tv_country_detail_borders = findViewById(R.id.country_detail_borders);

        // Set things for this view
        tv_country_detail_name.setText("Country: " + country.getName());
        tv_country_detail_capital.setText("Capital: " + country.getCapital());
        tv_country_detail_region.setText("Region: " + country.getRegion());
        tv_country_detail_population.setText("Population: " + country.getPopulation());
        tv_country_detail_area.setText("Area: " + country.getArea() + " km sq");
        tv_country_detail_borders.setText("Borders: " + country.getBorders().toString());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu. This adds items to the app bar.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent("The country " + country.getName() + " has the capital city " + country.getCapital());
        return super.onCreateOptionsMenu(menu);
    }

    private void setShareActionIntent(String text) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.my_device:
                Intent i = new Intent(this, MyDeviceActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        SvgLoader.pluck().close();
    }
}
