package com.tkdev.weatherapp.tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import com.tkdev.weatherapp.model.Weather;

import java.util.List;

import static com.tkdev.weatherapp.tasks.QueryWeather.TAG;
import static com.tkdev.weatherapp.tasks.Utils.WEATHER_API_KEY;
import static com.tkdev.weatherapp.tasks.Utils.WEATHER_API_PREFIX;
import static com.tkdev.weatherapp.tasks.Utils.WEATHER_CITY_ID;
import static com.tkdev.weatherapp.tasks.Utils.WEATHER_CURRENT_REQUEST;
import static com.tkdev.weatherapp.tasks.Utils.WEATHER_TEMPERATURE;
import static com.tkdev.weatherapp.tasks.Utils.WEATHER_TEMPERATURE_PREFIX;

public class WeatherCurrentTask extends AsyncTask<Void, Void, Weather> {


    @Override
    protected Weather doInBackground(Void... voids) {
        String uri = WEATHER_CURRENT_REQUEST +
                WEATHER_CITY_ID +
                WEATHER_TEMPERATURE_PREFIX +
                WEATHER_TEMPERATURE +
                WEATHER_API_PREFIX +
                WEATHER_API_KEY;

        Log.d(TAG, uri);
        return QueryWeather.fetchCurrent(uri);
    }

}

