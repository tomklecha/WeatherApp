package com.tkdev.weatherapp.presenter;

import android.widget.TextView;

import com.tkdev.weatherapp.model.Weather;
import com.tkdev.weatherapp.repository.WeatherRetrofitImpl;
import com.tkdev.weatherapp.tasks.WeatherCurrentTask;
import com.tkdev.weatherapp.tasks.WeatherForecastTask;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ForecastPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private List<Weather> forecasts;
    private WeatherRetrofitImpl retrofit;

    public ForecastPresenter(MainContract.View view) {
        this.view = view;
        this.retrofit = new WeatherRetrofitImpl();
    }

    //    private List<Weather> loadForecasts() {
//        try {
//            forecasts = new WeatherForecastTask().execute().get();
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//        return forecasts;
//    }
    private List<Weather> loadForecasts() {

        return retrofit.getForecasts();

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
