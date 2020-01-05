package com.tkdev.weatherapp.presenter;

import android.view.View;
import android.widget.TextView;

import com.tkdev.weatherapp.model.Weather;
import com.tkdev.weatherapp.tasks.WeatherCurrentTask;

import java.util.concurrent.ExecutionException;

public class CurrentPresenter implements MainContract.Presenter{

    private MainContract.View view;
    private Weather weather;
    private WeatherCurrentTask task;


    public CurrentPresenter(MainContract.View view) {
        this.view = view;
        this.weather = new Weather();
        this.task = new WeatherCurrentTask();
    }

    private Weather loadWeather() {
        task.execute();
        try {
            weather = task.get();

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return weather;
    }

    @Override
    public void onWeatherCreated() {
        loadWeather();
    }

    public void setTemperatureCurrentTextView(TextView textView) {
        textView.setText( String.valueOf(weather.getTemperatureCurrent()));
    }

    public void setTemperatureMinimumTextView(TextView textView) {
        textView.setText(String.valueOf(weather.getTemperatureMin()));
    }

    public void setTemperatureMaximumTextView(TextView textView) {
        textView.setText(String.valueOf(weather.getTemperatureMax()));
    }

    public void setWeatherDescriptionTextView(TextView textView) {
        textView.setText(String.valueOf(weather.getWeather()));
    }


    @Override
    public void onDestroy() {
        this.view = null;
    }

    // TestView text changers


    public void setHumidityViewText(TextView textView) {
        String setText = String.valueOf(weather.getHumidity());
        textView.setText(setText);
    }

}
