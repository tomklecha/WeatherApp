package com.tkdev.weatherapp.presenter;

import android.util.Log;
import android.widget.TextView;
import com.tkdev.weatherapp.model.Weather;
import com.tkdev.weatherapp.modelretro.WeatherRetrofit;
import com.tkdev.weatherapp.repository.WeatherRetrofitImpl;

import java.text.SimpleDateFormat;

import retrofit2.Response;

public class CurrentPresenter implements MainContract.Presenter, MainContract.APIListener {

    private static final String LAST_UPDATE_PATTERN = "HH:mm";
    private static final String DATE_PATTERN = "EEE dd-MM-yyyy";

    private MainContract.View view;
    private MainContract.Model model;
    private WeatherRetrofit weather;

    public CurrentPresenter(MainContract.View view) {
        this.view = view;
        this.weather = new WeatherRetrofit();
        this.model = new WeatherRetrofitImpl();
        }

    @Override
    public void onWeatherCreated() {
        model.getWeather(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    // TestView text changers


    public String setTemperatureCurrentTextView() {
       return (Math.rint((weather.getMain().getTemp()) * 10) / 10) + Weather.TEMPERATURE_SUFFIX;
    }

    public String  setTemperatureMinimumTextView() {
        return (Math.rint((weather.getMain().getTempMin()) * 10) / 10)+ Weather.TEMPERATURE_SUFFIX;

    }

    public String setTemperatureMaximumTextView() {
        return (Math.rint((weather.getMain().getTempMax()) * 10) / 10) + Weather.TEMPERATURE_SUFFIX;
    }

    public String setWeatherDescriptionTextView() {
       return weather.getWeather().get(0).getMain();
    }

    public String setHumidityViewText() {
       return  (weather.getMain().getHumidity()) + Weather.HUMIDITY_SUFFIX;
    }

    public String setLastUpdateViewText() {
        SimpleDateFormat lastUpdate = new SimpleDateFormat(LAST_UPDATE_PATTERN);
        return lastUpdate.format(weather.getDt()*1000);
    }
    public String setDateViewText() {
        SimpleDateFormat lastUpdate = new SimpleDateFormat(DATE_PATTERN);
       return lastUpdate.format(weather.getDt()*1000);
    }

    @Override
    public void onSuccess(Response<WeatherRetrofit> response) {
        weather = response.body();
    }

    @Override
    public void onFailure(Throwable t) {

    }
}
