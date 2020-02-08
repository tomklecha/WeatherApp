package com.tkdev.weatherapp.presenter;

import android.widget.TextView;
import com.tkdev.weatherapp.model.Weather;
import com.tkdev.weatherapp.repository.WeatherRetrofitImpl;

import java.text.SimpleDateFormat;

public class CurrentPresenter implements MainContract.Presenter {

    private static final String LAST_UPDATE_PATTERN = "HH:mm";
    private static final String DATE_PATTERN = "EEE dd-MM-yyyy";

    private MainContract.View view;
    private WeatherRetrofitImpl retrofit;
    private Weather weather;


    public CurrentPresenter(MainContract.View view) {
        this.view = view;
        this.weather = new Weather();
        this.retrofit = new WeatherRetrofitImpl();
    }

    private Weather loadWeather() {

        retrofit.getWeather();

        return new Weather();
    }
//    private Weather loadWeather() {
//        try {
//            weather = new WeatherCurrentTask().execute().get();
//
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        return weather;
//    }

    @Override
    public void onWeatherCreated() {
        loadWeather();
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    // TestView text changers

    public void setTemperatureCurrentTextView(TextView textView) {
        String setText = (weather.getTemperatureCurrent()) + Weather.TEMPERATURE_SUFFIX;
        textView.setText(setText);
    }

    public void setTemperatureMinimumTextView(TextView textView) {
        String setText = (weather.getTemperatureMin()) + Weather.TEMPERATURE_SUFFIX;
        textView.setText(setText);
    }

    public void setTemperatureMaximumTextView(TextView textView) {
        String setText = (weather.getTemperatureMax()) + Weather.TEMPERATURE_SUFFIX;
        textView.setText(setText);
    }

    public void setWeatherDescriptionTextView(TextView textView) {
        textView.setText(String.valueOf(weather.getWeather()));
    }

    public void setHumidityViewText(TextView textView) {
        String setText = (weather.getHumidity()) + Weather.HUMIDITY_SUFFIX;
        textView.setText(setText);
    }

    public void setLastUpdateViewText(TextView textView) {
        SimpleDateFormat lastUpdate = new SimpleDateFormat(LAST_UPDATE_PATTERN);
//        textView.setText(lastUpdate.format(weather.getDateOfLastUpdate()));
    }
    public void setDateViewText(TextView textView) {
        SimpleDateFormat lastUpdate = new SimpleDateFormat(DATE_PATTERN);
//        textView.setText(lastUpdate.format(weather.getDateOfLastUpdate()));
    }



}
