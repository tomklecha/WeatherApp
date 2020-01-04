package com.tkdev.weatherapp;

import java.util.Date;

public class Weather {

    private double temperatureCurrent;
    private double temperatureMin;
    private double temperatureMax;
    private int humidity;
    private Date dateOfLastUpdate;
    private String dayOfForecast;
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
        this.temperatureCurrent = temperatureCurrent;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(double temperatureMax) {
        this.temperatureMax = temperatureMax;
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

    public String getDayOfForecast() {
        return dayOfForecast;
    }

    public void setDayOfForecast(String dayOfForecast) {
        this.dayOfForecast = dayOfForecast;
    }

}
