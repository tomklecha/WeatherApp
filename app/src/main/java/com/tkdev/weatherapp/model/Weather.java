package com.tkdev.weatherapp.model;

import java.util.Date;

public class Weather {

    public static final String TEMPERATURE_SUFFIX = "°";
    public static final String HUMIDITY_SUFFIX = " %";

    private double temperatureCurrent;
    private double temperatureMin;
    private double temperatureMax;
    private int humidity;
    private Date dateOfLastUpdate;
    private Date dayOfForecast;
    private String weather;

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public double getTemperatureCurrent() {
        return temperatureCurrent;
    }

    public void setTemperatureCurrent(double temperatureCurrent) {
        this.temperatureCurrent = Math.rint(temperatureCurrent*10)/10;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(double temperatureMin) {
        this.temperatureMin = Math.rint(temperatureMin*10)/10;
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(double temperatureMax) {
        this.temperatureMax = Math.rint(temperatureMax*10)/10;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public Date getDateOfLastUpdate() {
        return dateOfLastUpdate;
    }

    public void setDateOfLastUpdate(Date dateOfLastUpdate) {
        this.dateOfLastUpdate = dateOfLastUpdate;
    }

    public Date getDayOfForecast() {
        return dayOfForecast;
    }

    public void setDayOfForecast(Date dayOfForecast) {
        this.dayOfForecast = dayOfForecast;
    }
}
