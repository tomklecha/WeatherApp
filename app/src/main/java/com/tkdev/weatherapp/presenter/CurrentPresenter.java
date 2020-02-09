package com.tkdev.weatherapp.presenter;

import com.tkdev.weatherapp.model.current_weather.WeatherRetrofit;
import com.tkdev.weatherapp.repository.WeatherRetrofitImpl;

import retrofit2.Response;

import static com.tkdev.weatherapp.repository.Utils.DATE_PATTERN;
import static com.tkdev.weatherapp.repository.Utils.HUMIDITY_SYMBOL;
import static com.tkdev.weatherapp.repository.Utils.LAST_UPDATE_PATTERN;
import static com.tkdev.weatherapp.repository.Utils.datePattern;
import static com.tkdev.weatherapp.repository.Utils.temperaturePrefix;

public class CurrentPresenter implements MainContract.Presenter, MainContract.APIListener {


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

    @Override
    public void onSuccess(Response<WeatherRetrofit> response) {
        weather = response.body();
    }

    @Override
    public void onFailure(Throwable t) {

    }

    // TestView text changers


    public String setTemperatureCurrentTextView() {
        return temperaturePrefix(weather.getMain().getTemp());
    }

    public String setTemperatureMinimumTextView() {
        return temperaturePrefix(weather.getMain().getTempMin());
    }

    public String setTemperatureMaximumTextView() {
        return temperaturePrefix(weather.getMain().getTempMax());
    }

    public String setWeatherDescriptionTextView() {
        return weather.getWeather().get(0).getMain();
    }

    public String setHumidityViewText() {
        return (weather.getMain().getHumidity()) + HUMIDITY_SYMBOL;
    }

    public String setLastUpdateViewText() {
        return datePattern(weather.getDt(), LAST_UPDATE_PATTERN);
    }

    public String setDateViewText() {
        return datePattern(weather.getDt(), DATE_PATTERN);
    }


    public String setCityName() {
        return weather.getName();
    }
}
