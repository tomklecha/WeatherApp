package com.tkdev.weatherapp.Common;

import com.tkdev.weatherapp.Common.BasePresenter;
import com.tkdev.weatherapp.Common.BaseView;
import com.tkdev.weatherapp.Current.model.WeatherRetrofit;
import com.tkdev.weatherapp.Forecast.model.ForecastRetrofit;

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
