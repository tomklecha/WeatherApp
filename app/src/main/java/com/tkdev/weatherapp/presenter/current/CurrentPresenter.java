package com.tkdev.weatherapp.presenter.current;

import com.tkdev.weatherapp.presenter.MainContract;

public interface CurrentPresenter extends MainContract {

    String setTemperatureCurrentTextView();
    String setTemperatureMinimumTextView();
    String setTemperatureMaximumTextView();
    String setWeatherDescriptionTextView();
    String setHumidityViewText();
    String setLastUpdateViewText();
    String setDateViewText();
    String setCityName();

    String sendCurrentWeather();
}
