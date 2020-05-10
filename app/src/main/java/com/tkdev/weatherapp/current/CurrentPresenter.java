package com.tkdev.weatherapp.current;

import com.tkdev.weatherapp.common.MainContract;

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
