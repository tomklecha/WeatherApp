package com.tkdev.weatherapp.model.forecast_weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForecastRetrofit {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private Integer message;
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<com.tkdev.weatherapp.model.forecast_weather.List> list = null;
    @SerializedName("city")
    @Expose
    private City city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public ForecastRetrofit withCod(String cod) {
        this.cod = cod;
        return this;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public ForecastRetrofit withMessage(Integer message) {
        this.message = message;
        return this;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public ForecastRetrofit withCnt(Integer cnt) {
        this.cnt = cnt;
        return this;
    }

    public java.util.List<com.tkdev.weatherapp.model.forecast_weather.List> getList() {
        return list;
    }

    public void setList(java.util.List<com.tkdev.weatherapp.model.forecast_weather.List> list) {
        this.list = list;
    }

    public ForecastRetrofit withList(java.util.List<com.tkdev.weatherapp.model.forecast_weather.List> list) {
        this.list = list;
        return this;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public ForecastRetrofit withCity(City city) {
        this.city = city;
        return this;
    }

}