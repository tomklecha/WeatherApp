package com.tkdev.weatherapp;

import android.os.AsyncTask;

import com.tkdev.weatherapp.model.Weather;

import static com.tkdev.weatherapp.MainActivity.WEATHER_API_KEY;
import static com.tkdev.weatherapp.MainActivity.WEATHER_API_PREFIX;
import static com.tkdev.weatherapp.MainActivity.WEATHER_CITY_ID;
import static com.tkdev.weatherapp.MainActivity.WEATHER_CURRENT_REQUEST;
import static com.tkdev.weatherapp.MainActivity.WEATHER_TEMPERATURE;
import static com.tkdev.weatherapp.MainActivity.WEATHER_TEMPERATURE_PREFIX;

public class WeatherCurrentTask extends AsyncTask <Void, Void, Weather> {


    @Override
    protected Weather doInBackground(Void... voids) {
        String uri = WEATHER_CURRENT_REQUEST +
                WEATHER_CITY_ID +
                WEATHER_TEMPERATURE_PREFIX +
                WEATHER_TEMPERATURE +
                WEATHER_API_PREFIX +
                WEATHER_API_KEY;

     return QueryWeather.fetchCurrent(uri);
    }
    }

