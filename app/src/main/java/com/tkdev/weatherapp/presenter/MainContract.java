package com.tkdev.weatherapp.presenter;

import com.tkdev.weatherapp.model.current_weather.WeatherRetrofit;
import com.tkdev.weatherapp.model.forecast_weather.ForecastRetrofit;

import retrofit2.Response;

public interface MainContract {

    interface Model {
        void getWeather(final APIListener listener, String city);
        void getWeather(final APIForecastListener listener, String city);
    }

    interface Presenter extends BasePresenter {
        void onRequestWeather(String city);
    }

    interface View extends BaseView<Presenter>{
        void showWeatherByCity(String city);
        void update();
        void cancelUpdate();
        void onFailUpdate(String message);
    }
    interface APIListener {
        void onSuccessResponse(Response<WeatherRetrofit> response);
        void onFailureResponse(String responseMessage);
    }
    interface APIForecastListener {
        void onSuccessResponse(Response<ForecastRetrofit> response);
        void onFailureResponse(String responseMessage);
    }
  }
