package com.tkdev.weatherapp.presenter;

import android.widget.TextView;

import com.tkdev.weatherapp.model.Weather;
import com.tkdev.weatherapp.tasks.WeatherCurrentTask;
import com.tkdev.weatherapp.tasks.WeatherForecastTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ForecastPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private List<Weather> forecasts;
    private WeatherForecastTask task;


    public ForecastPresenter(MainContract.View view) {
        this.view = view;
        this.task = new WeatherForecastTask();
    }

    private List<Weather> loadForecasts() {
        task.execute();
        try {
            this.forecasts = task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return this.forecasts;
    }

    @Override
    public void onWeatherCreated() {
        loadForecasts();
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    public List<Weather> getForecasts() {
        return forecasts;
    }
}
