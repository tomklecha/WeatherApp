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


    @Override
    public void onDestroy() {
        this.view = null;
    }

    public void setCurrentViewText(TextView view) {
        String textView = String.valueOf(weather.getTemperatureCurrent());
        view.setText(textView);
    }

}
