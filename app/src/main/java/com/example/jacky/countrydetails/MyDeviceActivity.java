package com.example.jacky.countrydetails;

import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class MyDeviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_device);

        TextView tv_my_device_manufacturer = findViewById(R.id.my_device_manufacturer);
        TextView tv_my_device_model = findViewById(R.id.my_device_model);
        TextView tv_my_device_version = findViewById(R.id.my_device_version);
        TextView tv_my_device_version_release = findViewById(R.id.my_device_version_release);
        TextView tv_my_device_serial_number = findViewById(R.id.my_device_serial_number);

        tv_my_device_manufacturer.setText("Manufacturer: " + Build.MANUFACTURER);
        tv_my_device_model.setText("Model: " + Build.MODEL);
        tv_my_device_version.setText("Version: " + Build.VERSION.SDK_INT);
        tv_my_device_version_release.setText("Version Release: " + Build.VERSION.RELEASE);
        tv_my_device_serial_number.setText("Serial Number: " + Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID));


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
