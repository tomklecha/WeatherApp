package com.tkdev.weatherapp.model;

public class Weather {

    private final String weatherDescription;
    private final double temperatureCurrent;
    private final double temperatureMin;
    private final double temperatureMax;


    public Weather(String weatherDescription, double temperatureCurrent, double temperatureMin, double temperatureMax) {
        this.weatherDescription = weatherDescription;
        this.temperatureCurrent = temperatureCurrent;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public double getTemperatureCurrent() {
        return temperatureCurrent;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "weatherDescription='" + weatherDescription + '\'' +
                ", temperatureCurrent=" + temperatureCurrent +
                ", temperatureMin=" + temperatureMin +
                ", temperatureMax=" + temperatureMax +
                '}';
    }
}
