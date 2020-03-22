package com.tkdev.weatherapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;

public class RetrofitCalls {




    public static final String AND_SYMBOL = "&";
    public static final String WEATHER_REQUEST_BASE = "https://api.openweathermap.org/data/2.5/";
    public static final String WEATHER_CURRENT_REQUEST = "weather?";
    public static final String WEATHER_FORECAST_REQUEST = "forecast?";
    public static final String WEATHER_CITY_ID_PREFIX = "id";
    public static final String WEATHER_CITY_NAME = "q";
    public static final String WEATHER_CITY_ID = "2643743";
    public static final String WEATHER_CITY_ID_RETROFIT = "{city}";
    public static final String WEATHER_API_PREFIX = "APPID=";
    public static final String WEATHER_API_KEY = "5b08b54ce198509d241991110864cab4";
    public static final String WEATHER_TEMPERATURE_PREFIX = "units=";
    public static final String WEATHER_TEMPERATURE_CELSIUS = "metric";

    public static final String CELSIUS_SYMBOL = "°";
    public static final String HUMIDITY_SYMBOL = " %";
    public static final String FORECAST_DAY_PATTERN = "EEE";
    public static final String FORECAST_HOUR_PATTERN = "HH:mm";
    public static final String LAST_UPDATE_PATTERN = "HH:mm";
    public static final String DATE_PATTERN = "EEE dd-MM-yyyy";


    public static String temperaturePrefix(double temp) {
        return (Math.rint(temp * 10) / 10) + CELSIUS_SYMBOL;
    }

    public static String datePattern(Integer dt, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Long date = Long.valueOf(dt) * 1000;
        return dateFormat.format(date);
    }

    public static void makeSnack(View view, String message){
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

}
