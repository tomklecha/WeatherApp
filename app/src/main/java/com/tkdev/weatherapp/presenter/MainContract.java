package com.tkdev.weatherapp.presenter;

import com.tkdev.weatherapp.model.current_weather.WeatherRetrofit;
import com.tkdev.weatherapp.model.forecast_weather.ForecastRetrofit;

import retrofit2.Response;

public interface MainContract {

    interface Model {
        void getWeather(final APIListener listener);	// Retrieve list of movies
        void getForecast(final APIForecastListener listener);	// Retrieve list of movies
    }

    interface Presenter extends BasePresenter {
        void onWeatherCreated();
    }

    interface View extends BaseView<Presenter>{
        void refreshViews();
        void setText(String setText);
    }
    interface APIListener {
        void onSuccess(Response<WeatherRetrofit> response);
        void onFailure(Throwable t);
    }
    interface APIForecastListener {
        void onSuccess(Response<ForecastRetrofit> response);
        void onFailure(Throwable t);
    }
  }
