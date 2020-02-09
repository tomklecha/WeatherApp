package com.tkdev.weatherapp.presenter;

import com.tkdev.weatherapp.model.current_weather.WeatherRetrofit;
import com.tkdev.weatherapp.model.forecast_weather.ForecastRetrofit;
import com.tkdev.weatherapp.repository.WeatherRetrofitImpl;

import retrofit2.Response;

public class ForecastPresenter implements MainContract.Presenter, MainContract.APIForecastListener {

    private MainContract.View view;
    private ForecastRetrofit forecasts;
    private MainContract.Model model;
    private WeatherRetrofit forecastCallback;


    public ForecastPresenter(MainContract.View view) {
        this.view = view;
        this.model = new WeatherRetrofitImpl();
        this.forecastCallback = new WeatherRetrofit();
    }

    public WeatherRetrofit getForecasts() {
        return null;
    }

    @Override
    public void onWeatherCreated() {
        model.getForecast(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }


    @Override
    public void onSuccess(Response<ForecastRetrofit> response) {
        forecasts = response.body();
    }

    @Override
    public void onFailure(Throwable t) {

    }
}
