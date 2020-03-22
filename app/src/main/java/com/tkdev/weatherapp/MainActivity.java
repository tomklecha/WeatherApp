package com.tkdev.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tkdev.weatherapp.dialogs.AboutDialog;
import com.tkdev.weatherapp.dialogs.SearchDialog;
import com.tkdev.weatherapp.dialogs.ShareDialog;
import com.tkdev.weatherapp.fragments.WeatherCurrentFragment;
import com.tkdev.weatherapp.fragments.WeatherForecastFragment;
import com.tkdev.weatherapp.presenter.MainContract;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import static com.tkdev.weatherapp.utils.PreferencesVariables.current_city;


public class MainActivity extends AppCompatActivity implements
        SearchDialog.SearchDialogListener,
        ShareDialog.ShareDialogListener
{

    private static final String TAG = "WeatherMainActivity";

    private MainContract.View currentListener;
    private MainContract.View forecastListener;
    private DialogFragment dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbarSetup();
        showBasicFragments();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh_weather:
                currentListener.showWeatherByCity(current_city);
                forecastListener.showWeatherByCity(current_city);
                return true;

            case R.id.menu_app_bar_search:
                dialog = new SearchDialog();
                dialog.show(getSupportFragmentManager(), "SearchDialog");
                return true;

            case R.id.menu_share:
                dialog = new ShareDialog();
                dialog.show(getSupportFragmentManager(), "ShareDialog");
                return true;

            case R.id.menu_about_dev:
                dialog = new AboutDialog();
                dialog.show(getSupportFragmentManager(), "AboutDialog");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        if (fragment instanceof WeatherCurrentFragment) {
            currentListener = (MainContract.View) fragment;
        }
        if (fragment instanceof WeatherForecastFragment) {
            forecastListener = (MainContract.View) fragment;
        }
    }

    private void toolbarSetup() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
    }

    private void showBasicFragments() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_current, new WeatherCurrentFragment())
                .commit();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_forecast, new WeatherForecastFragment())
                .commit();
    }


    private void shareWeather(ArrayList<Boolean> booleanList) {
        Intent weatherIntent = new Intent();
        weatherIntent.setAction(Intent.ACTION_SEND);
        weatherIntent.putExtra(Intent.EXTRA_TEXT, currentListener.shareWeather(booleanList));
        weatherIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(weatherIntent, null);
        startActivity(shareIntent);
    }

    @Override
    public void onSearchPositiveClick(@NotNull String city) {
        currentListener.showWeatherByCity(city);
        forecastListener.showWeatherByCity(city);
    }

    @Override
    public void onSearchNegativeClick(@NotNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSharePositiveClick(@NotNull ArrayList<Boolean> list) {
        shareWeather(list);
    }

    @Override
    public void onShareNegativeClick(@NotNull String message) {

    }

    // TODO !!!
//    private boolean connectivity() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED) {
//                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//                return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
//                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
//            }
//        }
//    }
}