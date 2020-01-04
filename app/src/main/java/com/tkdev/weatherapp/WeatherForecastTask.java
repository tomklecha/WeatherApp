package com.tkdev.weatherapp;

import android.os.AsyncTask;
import android.util.Log;

import com.tkdev.weatherapp.model.WeatherForecast;

import java.util.List;

import static com.tkdev.weatherapp.MainActivity.WEATHER_API_KEY;
import static com.tkdev.weatherapp.MainActivity.WEATHER_API_PREFIX;
import static com.tkdev.weatherapp.MainActivity.WEATHER_CITY_ID;
import static com.tkdev.weatherapp.MainActivity.WEATHER_FORECAST_REQUEST;
import static com.tkdev.weatherapp.MainActivity.WEATHER_TEMPERATURE;
import static com.tkdev.weatherapp.MainActivity.WEATHER_TEMPERATURE_PREFIX;
import static com.tkdev.weatherapp.QueryWeather.TAG;

public class WeatherForecastTask extends AsyncTask<Void, Void, List<WeatherForecast>> {


    @Override
    protected List<WeatherForecast> doInBackground(Void... voids) {
        String uri = WEATHER_FORECAST_REQUEST +
                WEATHER_CITY_ID +
                WEATHER_TEMPERATURE_PREFIX +
                WEATHER_TEMPERATURE +
                WEATHER_API_PREFIX +
                WEATHER_API_KEY;

        List<WeatherForecast> forecasts = QueryWeather.fetchForecasts(uri);
        Log.d(TAG, uri);
        return forecasts;
    }
}
