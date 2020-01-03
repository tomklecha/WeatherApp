package com.tkdev.weatherapp.model;

import java.util.Date;

public class WeatherCurrent extends Weather {

    private int humidity;
    private Date dateOfLastUpdate;

    public WeatherCurrent(String weatherDescription, double temperatureCurrent, double temperatureMin, double temperatureMax, int humidity, Date dateOfLastUpdate) {
        super(weatherDescription, temperatureCurrent, temperatureMin, temperatureMax);
        this.humidity = humidity;
        this.dateOfLastUpdate = dateOfLastUpdate;
    }

    @Override
    public String toString() {
        return "WeatherCurrent{" +
                "humidity=" + humidity +
                ", dateOfLastUpdate=" + dateOfLastUpdate +
                '}';
    }
}
