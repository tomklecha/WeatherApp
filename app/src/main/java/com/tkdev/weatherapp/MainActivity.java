package com.tkdev.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.tkdev.weatherapp.fragments.WeatherCurrentFragment;
import com.tkdev.weatherapp.fragments.WeatherForecastFragment;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "WeatherMainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbarSetup();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_current, new WeatherCurrentFragment())
                .commit();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_forecast, new WeatherForecastFragment())
                .commit();

    }

    private void toolbarSetup() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_dev:
                showAboutDialog();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    private void showAboutDialog() {
        AppCompatDialog appCompatDialog = new AppCompatDialog(this);
        appCompatDialog.setContentView(R.layout.about_dev);
        appCompatDialog.show();
    }
}