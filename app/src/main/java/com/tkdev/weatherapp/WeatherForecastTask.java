package com.tkdev.weatherapp;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import static com.tkdev.weatherapp.QueryWeather.TAG;
import static com.tkdev.weatherapp.Utils.WEATHER_API_KEY;
import static com.tkdev.weatherapp.Utils.WEATHER_API_PREFIX;
import static com.tkdev.weatherapp.Utils.WEATHER_CITY_ID;
import static com.tkdev.weatherapp.Utils.WEATHER_FORECAST_REQUEST;
import static com.tkdev.weatherapp.Utils.WEATHER_TEMPERATURE;
import static com.tkdev.weatherapp.Utils.WEATHER_TEMPERATURE_PREFIX;

public class WeatherForecastTask extends AsyncTask<Void, Void, List<Weather>> {


    @Override
    protected List<Weather> doInBackground(Void... voids) {
        String uri = WEATHER_FORECAST_REQUEST +
                WEATHER_CITY_ID +
                WEATHER_TEMPERATURE_PREFIX +
                WEATHER_TEMPERATURE +
                WEATHER_API_PREFIX +
                WEATHER_API_KEY;

        List<Weather> forecasts = QueryWeather.fetchForecasts(uri);
        Log.d(TAG, uri);
        return forecasts;
    }
}
