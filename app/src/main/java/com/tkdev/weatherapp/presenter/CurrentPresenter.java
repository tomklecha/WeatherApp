package com.tkdev.weatherapp.presenter;

import android.view.View;

import com.tkdev.weatherapp.Weather;
import com.tkdev.weatherapp.WeatherCurrentTask;

import java.util.concurrent.ExecutionException;

public class CurrentPresenter implements MainContract.Presenter {

    private View view;
    private WeatherCurrentTask task;

    public CurrentPresenter(View view, WeatherCurrentTask task) {
        this.view = view;
        this.task = new WeatherCurrentTask();
    }


    @Override
    public Weather onViewCreated() {
        Weather weather = new Weather();
        return loadWeather(weather);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    private Weather loadWeather(Weather weather) {
        task.execute();
        try {
            weather = task.get();

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return weather;
    }
}
