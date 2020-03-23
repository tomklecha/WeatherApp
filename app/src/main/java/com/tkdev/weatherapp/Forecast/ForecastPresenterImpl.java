package com.tkdev.weatherapp.Forecast;

import com.tkdev.weatherapp.Forecast.model.ForecastRetrofit;
import com.tkdev.weatherapp.Common.MainContract;
import com.tkdev.weatherapp.Common.WeatherRetrofitImpl;

import retrofit2.Response;

import static com.tkdev.weatherapp.Common.PreferencesVariables.current_city;
import static com.tkdev.weatherapp.Common.PreferencesVariables.last_dt;

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
            current_city = "london";
            model.getWeather(this, current_city);
        } else if (
                last_dt <= System.currentTimeMillis() / 1000 - 600
                        ||
                        (!(current_city.equals(city))))
        {
            model.getWeather(this, city);
        } else {
            view.cancelUpdate();
        }
    }
}
