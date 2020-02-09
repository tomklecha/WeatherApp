package com.tkdev.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.tkdev.weatherapp.fragments.WeatherCurrentFragment;
import com.tkdev.weatherapp.fragments.WeatherForecastFragment;
import com.tkdev.weatherapp.presenter.CurrentPresenter;
import com.tkdev.weatherapp.presenter.MainContract;

import java.util.Objects;


public class MainActivity extends AppCompatActivity implements MainContract.View{

    private static final String TAG = "WeatherMainActivity";

    private MainContract.View currentListener;
    private MainContract.View forecastListener;
    private MainContract.Presenter presenter;


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

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        if (fragment instanceof WeatherCurrentFragment){
            currentListener = (MainContract.View) fragment;
        }
        if (fragment instanceof WeatherForecastFragment){
            forecastListener = (MainContract.View) fragment;
        }
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
//                showAboutDialog();
                currentListener.refreshViews();
//                forecastListener.refreshViews();
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

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = (CurrentPresenter) presenter;
    }

    @Override
    public void refreshViews() {
        presenter.onWeatherCreated();

    }

    @Override
    public void setText(String setText) {

    }
}