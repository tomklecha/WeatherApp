package com.tkdev.weatherapp.presenter;

import android.view.View;

import com.tkdev.weatherapp.Weather;
import com.tkdev.weatherapp.WeatherCurrentTask;
import com.tkdev.weatherapp.WeatherForecastTask;

import java.util.concurrent.ExecutionException;

public class ForecastPresenter implements MainContract.Presenter {

    private View view;
    private WeatherCurrentTask task;

    public ForecastPresenter(View view, WeatherForecastTask task) {
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
