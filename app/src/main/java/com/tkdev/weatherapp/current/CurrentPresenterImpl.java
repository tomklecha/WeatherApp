package com.tkdev.weatherapp.current;

import com.tkdev.weatherapp.current.model.WeatherRetrofit;
import com.tkdev.weatherapp.common.MainContract;
import com.tkdev.weatherapp.common.WeatherRetrofitImpl;

import retrofit2.Response;

import static com.tkdev.weatherapp.common.RetrofitCalls.DATE_PATTERN;
import static com.tkdev.weatherapp.common.RetrofitCalls.HUMIDITY_SYMBOL;
import static com.tkdev.weatherapp.common.RetrofitCalls.LAST_UPDATE_PATTERN;
import static com.tkdev.weatherapp.common.PreferencesVariables.current_city;
import static com.tkdev.weatherapp.common.RetrofitCalls.datePattern;
import static com.tkdev.weatherapp.common.PreferencesVariables.last_dt;
import static com.tkdev.weatherapp.common.RetrofitCalls.temperaturePrefix;

public class CurrentPresenterImpl implements MainContract.Presenter, MainContract.APIListener, CurrentPresenter {


    private MainContract.View view;
    private MainContract.Model model;
    private WeatherRetrofit weather;

    public CurrentPresenterImpl(MainContract.View view) {
        this.view = view;
        this.model = new WeatherRetrofitImpl();
        this.weather = new WeatherRetrofit();
    }


    @Override
    public void onRequestWeather(String city) {


        if (current_city.equals("")) {
            current_city = "london";
            model.getWeather(this, current_city);
        } else if (
                last_dt <= System.currentTimeMillis() / 1000 - 600
                        ||
                        (!(current_city.equals(city)))) {
            model.getWeather(this, city);
        } else {
            view.cancelUpdate();
        }
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void onSuccessResponse(Response<WeatherRetrofit> response) {
        weather = response.body();
        last_dt = weather.getDt();
        current_city = weather.getName();
        view.update();
    }

    @Override
    public void onFailureResponse(String message) {
        view.onFailUpdate(message);
    }

    // TestView text changers

    @Override
    public String setTemperatureCurrentTextView() {
        return temperaturePrefix(weather.getMain().getTemp());
    }

    @Override
    public String setTemperatureMinimumTextView() {
        return temperaturePrefix(weather.getMain().getTempMin());
    }

    @Override
    public String setTemperatureMaximumTextView() {
        return temperaturePrefix(weather.getMain().getTempMax());
    }

    @Override
    public String setWeatherDescriptionTextView() {
        return weather.getWeather().get(0).getMain();
    }

    @Override
    public String setHumidityViewText() {
        return (weather.getMain().getHumidity()) + HUMIDITY_SYMBOL;
    }

    @Override
    public String setLastUpdateViewText() {
        return datePattern(weather.getDt() + weather.getTimezone(), LAST_UPDATE_PATTERN);
    }

    @Override
    public String setDateViewText() {
        return datePattern((int) (System.currentTimeMillis() / 1000), DATE_PATTERN);
    }

    @Override
    public String setCityName() {
        return weather.getName();
    }

    @Override
    public String sendCurrentWeather() {
        StringBuilder weatherShare = new StringBuilder();
        weatherShare.append(current_city)
                .append(" ")
                .append(weather.getMain().getTemp());

        return weatherShare.toString();
    }
}
