package com.tkdev.weatherapp.common;

import com.tkdev.weatherapp.current.model.WeatherRetrofit;
import com.tkdev.weatherapp.forecast.model.ForecastRetrofit;

import java.util.ArrayList;

import retrofit2.Response;

public interface MainContract {

    interface Model {
        void getWeather(final APIListener listener, String city);
        void getWeather(final APIForecastListener listener, String city);
    }

    interface Presenter extends BasePresenter {
        void onRequestWeather(String city);
    }

    interface View extends BaseView<Presenter> {
        void showWeatherByCity(String city);
        void update();
        void cancelUpdate();
        void onFailUpdate(String message);

        String shareWeather(ArrayList<Boolean> booleanList);
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
