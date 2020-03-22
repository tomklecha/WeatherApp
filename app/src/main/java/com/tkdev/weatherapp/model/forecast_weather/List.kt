package com.tkdev.weatherapp.model.forecast_weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.tkdev.weatherapp.model.current_weather.Main
import com.tkdev.weatherapp.model.current_weather.Sys
import com.tkdev.weatherapp.model.current_weather.Weather
import com.tkdev.weatherapp.model.current_weather.Wind

class List {
    @SerializedName("dt")
    @Expose
    var dt: Int = 0

    @SerializedName("main")
    @Expose
    var main: Main? = null

    @SerializedName("weather")
    @Expose
    var weather: kotlin.collections.List<Weather>? = null

    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null

    @SerializedName("wind")
    @Expose
    var wind: Wind? = null

    @SerializedName("rain")
    @Expose
    var rain: Rain? = null

    @SerializedName("sys")
    @Expose
    var sys: Sys? = null

    @SerializedName("dt_txt")
    @Expose
    var dtTxt: String? = null

    @SerializedName("snow")
    @Expose
    var snow: Snow? = null

    fun withDt(dt: Int): List {
        this.dt = dt
        return this
    }

    fun withMain(main: Main?): List {
        this.main = main
        return this
    }

    fun withWeather(weather: kotlin.collections.List<Weather>?): List {
        this.weather = weather
        return this
    }

    fun withClouds(clouds: Clouds?): List {
        this.clouds = clouds
        return this
    }

    fun withWind(wind: Wind?): List {
        this.wind = wind
        return this
    }

    fun withRain(rain: Rain?): List {
        this.rain = rain
        return this
    }

    fun withSys(sys: Sys?): List {
        this.sys = sys
        return this
    }

    fun withDtTxt(dtTxt: String?): List {
        this.dtTxt = dtTxt
        return this
    }

    fun withSnow(snow: Snow?): List {
        this.snow = snow
        return this
    }
}