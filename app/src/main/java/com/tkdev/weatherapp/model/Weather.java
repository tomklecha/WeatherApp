package com.tkdev.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Weather {

    public static final String TEMPERATURE_SUFFIX = "°";
    public static final String HUMIDITY_SUFFIX = " %";


//    @SerializedName("temp")
    private double temperatureCurrent;

//    @SerializedName("temp_min")
    private double temperatureMin;

//    @SerializedName("temp_max")
    private double temperatureMax;

//    @SerializedName("humidity")
    private int humidity;

//    @SerializedName("dt")
    private Date date;

//    @SerializedName("weather")
    private String weatherDescription;

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }



    public double getTemperatureCurrent() {
        return temperatureCurrent;
    }

    public void setTemperatureCurrent(double temperatureCurrent) {
        this.temperatureCurrent = Math.rint(temperatureCurrent * 10) / 10;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(double temperatureMin) {
        this.temperatureMin = Math.rint(temperatureMin * 10) / 10;
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(double temperatureMax) {
        this.temperatureMax = Math.rint(temperatureMax * 10) / 10;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
