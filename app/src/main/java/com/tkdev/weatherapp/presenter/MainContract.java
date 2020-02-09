package com.tkdev.weatherapp.presenter;

import android.view.View;
import android.widget.TextView;

import com.tkdev.weatherapp.model.Weather;
import com.tkdev.weatherapp.modelretro.WeatherRetrofit;

import java.util.ArrayList;

import retrofit2.Response;

public interface MainContract {

    interface Model {
        void getWeather(final APIListener listener);	// Retrieve list of movies
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

}
