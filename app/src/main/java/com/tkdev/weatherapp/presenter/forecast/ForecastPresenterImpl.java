package com.tkdev.weatherapp.presenter.forecast;

import android.util.Log;

import com.tkdev.weatherapp.model.forecast_weather.ForecastRetrofit;
import com.tkdev.weatherapp.presenter.MainContract;
import com.tkdev.weatherapp.repository.WeatherRetrofitImpl;

import java.util.List;

import retrofit2.Response;

import static com.tkdev.weatherapp.repository.Utils.current_city;
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
    public void onSuccessResponse(Response<ForecastRetrofit> response) {
        forecasts = response.body();
        view.update();
    }



    @Override
    public void onFailureResponse(String responseMessage) {
//        view.onFailUpdate(responseMessage);
    }


    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onRequestWeather(String city) {
        if (current_city.equals("")) {
            current_city = "London";
            model.getWeather(this, current_city);
        }
        else {
            model.getWeather(this, city);
        } 
    }
}
