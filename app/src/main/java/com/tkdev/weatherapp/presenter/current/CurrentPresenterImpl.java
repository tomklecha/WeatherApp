package com.tkdev.weatherapp.presenter.current;

import android.util.Log;

import com.tkdev.weatherapp.model.current_weather.WeatherRetrofit;
import com.tkdev.weatherapp.presenter.MainContract;
import com.tkdev.weatherapp.repository.WeatherRetrofitImpl;

import java.time.LocalDate;

import retrofit2.Response;

import static com.tkdev.weatherapp.repository.Utils.DATE_PATTERN;
import static com.tkdev.weatherapp.repository.Utils.HUMIDITY_SYMBOL;
import static com.tkdev.weatherapp.repository.Utils.LAST_UPDATE_PATTERN;
import static com.tkdev.weatherapp.repository.Utils.datePattern;
import static com.tkdev.weatherapp.repository.Utils.last_dt;
import static com.tkdev.weatherapp.repository.Utils.temperaturePrefix;

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
    public void onWeatherCreated() {
        Log.d("PREF=onSuccess", String.valueOf(last_dt));
        Log.d("PREF=onSuccess", String.valueOf(System.currentTimeMillis()));

        if(last_dt <= System.currentTimeMillis()/1000 - 600)  {
            model.getWeather(this);
        }else {
            view.cancelUpdate();
        }
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void onSuccess(Response<WeatherRetrofit> response) {
        weather = response.body();
        last_dt = weather.getDt();
        Log.d("PREF=onSuccess",datePattern(weather.getDt(), LAST_UPDATE_PATTERN));
        Log.d("PREF=onSuccess",weather.getDt().toString());
        view.update();
    }

    @Override
    public void onFailure(Throwable t) {

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
        return datePattern(weather.getDt(), LAST_UPDATE_PATTERN);
    }

    @Override
    public String setDateViewText() {
        return datePattern(weather.getDt(), DATE_PATTERN);
    }

    @Override
    public String setCityName() {
        return weather.getName();
    }
}
