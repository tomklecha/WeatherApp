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

    public ForecastPresenter(MainContract.View view) {
        this.view = view;
    }

    private List<Weather> loadForecasts() {
        try {
            forecasts = new WeatherForecastTask().execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return forecasts;
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
