package com.tkdev.weatherapp.presenter.forecast;

import android.util.Log;

import com.tkdev.weatherapp.model.forecast_weather.ForecastRetrofit;
import com.tkdev.weatherapp.presenter.MainContract;
import com.tkdev.weatherapp.repository.WeatherRetrofitImpl;

import retrofit2.Response;

import static com.tkdev.weatherapp.repository.Utils.last_dt;

public class ForecastPresenterImpl implements MainContract.Presenter, ForecastPresenter, MainContract.APIForecastListener {

    private MainContract.View view;
    private ForecastRetrofit forecasts;
    private MainContract.Model model;

    public ForecastPresenterImpl(MainContract.View view) {
        this.view = view;
        this.model = new WeatherRetrofitImpl();
        this.forecasts = new ForecastRetrofit();
    }

    public ForecastRetrofit getForecasts() {
        return forecasts;
    }

    @Override
    public void onSuccess(Response<ForecastRetrofit> response) {
        forecasts = response.body();
        view.update();
    }

    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void onWeatherCreated() {
        model.getForecast(this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
