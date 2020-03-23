package com.tkdev.weatherapp.Current;

import com.tkdev.weatherapp.Common.MainContract;

public interface CurrentPresenterInterface extends MainContract {

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
