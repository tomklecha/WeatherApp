package com.tkdev.weatherapp.presenter;

import android.view.View;
import android.widget.TextView;

import com.tkdev.weatherapp.model.Weather;
import com.tkdev.weatherapp.tasks.WeatherCurrentTask;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;

public class CurrentPresenter implements MainContract.Presenter {

    private static final String CURRENT_DATE_PATTERN = "HH:mm";
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
        String setText = (weather.getTemperatureCurrent()) + Weather.TEMPERATURE_SUFFIX;
        textView.setText(setText);
    }

    public void setTemperatureMinimumTextView(TextView textView) {
        String setText = (weather.getTemperatureMin()) + Weather.TEMPERATURE_SUFFIX;
        textView.setText(setText);
    }

    public void setTemperatureMaximumTextView(TextView textView) {
        String setText = (weather.getTemperatureCurrent()) + Weather.TEMPERATURE_SUFFIX;
        textView.setText(setText);
    }

    public void setWeatherDescriptionTextView(TextView textView) {
        textView.setText(String.valueOf(weather.getWeather()));
    }

    public void setHumidityViewText(TextView textView) {
        String setText = (weather.getHumidity()) + Weather.TEMPERATURE_SUFFIX;
        textView.setText(setText);
    }

    public void setDateUpdateViewText(TextView textView) {
        SimpleDateFormat lastUpdate = new SimpleDateFormat(CURRENT_DATE_PATTERN);
        textView.setText(lastUpdate.format(weather.getDateOfLastUpdate()));
    }


    @Override
    public void onDestroy() {
        this.view = null;
    }

    // TestView text changers


}
