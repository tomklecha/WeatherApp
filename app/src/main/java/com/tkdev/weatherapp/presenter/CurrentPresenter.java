package com.tkdev.weatherapp.presenter;

import com.tkdev.weatherapp.model.current_weather.WeatherRetrofit;
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

    @Override
    public void onSuccess(Response<WeatherRetrofit> response) {
        weather = response.body();
    }

    @Override
    public void onFailure(Throwable t) {

    }

    // TestView text changers


    public String setTemperatureCurrentTextView() {
        return (Math.rint((weather.getMain().getTemp()) * 10) / 10) +"";
    }

    public String setTemperatureMinimumTextView() {
        return (Math.rint((weather.getMain().getTempMin()) * 10) / 10) + "";

    }

    public String setTemperatureMaximumTextView() {
        return (Math.rint((weather.getMain().getTempMax()) * 10) / 10) + "";
    }

    public String setWeatherDescriptionTextView() {
        return weather.getWeather().get(0).getMain();
    }

    public String setHumidityViewText() {
        return (weather.getMain().getHumidity()) + "";
    }

    public String setLastUpdateViewText() {
        SimpleDateFormat lastUpdate = new SimpleDateFormat(LAST_UPDATE_PATTERN);
        Long date = Long.valueOf(weather.getDt());
        return lastUpdate.format(date * 1000);
    }

    public String setDateViewText() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        Long date = Long.valueOf(weather.getDt());
        return dateFormat.format(date * 1000);
    }
}
