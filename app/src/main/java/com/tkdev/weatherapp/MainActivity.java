package com.tkdev.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

     static final String WEATHER_CURRENT_REQUEST = "https://api.openweathermap.org/data/2.5/weather?id=";
     static final String WEATHER_FORECAST_REQUEST = "https://api.openweathermap.org/data/2.5/forecast?id=";
     static final String WEATHER_CITY_ID = "2643743";
     static final String WEATHER_API_PREFIX = "&APPID=";
     static final String WEATHER_API_KEY = "5b08b54ce198509d241991110864cab4";
     static final String WEATHER_TEMPERATURE = "metric";
     static final String WEATHER_TEMPERATURE_PREFIX = "&units=";

    private static final String TAG = "WeatherMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new WeatherForecastTask().execute();
        new WeatherCurrentTask().execute();





    }
}
