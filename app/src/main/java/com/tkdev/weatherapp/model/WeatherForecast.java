package com.tkdev.weatherapp.model;

import java.util.Date;

public class WeatherForecast extends Weather {

    private String dayOfForecast;

    public WeatherForecast(String weatherDescription, double temperatureCurrent, double temperatureMin, double temperatureMax, String dayOfForecast) {
        super(weatherDescription, temperatureCurrent, temperatureMin, temperatureMax);
        this.dayOfForecast = dayOfForecast;
    }

    @Override
    public String toString() {
        return "WeatherForecast{" +
                "dayOfForecast='" + dayOfForecast + '\'' +
                '}';
    }
}
