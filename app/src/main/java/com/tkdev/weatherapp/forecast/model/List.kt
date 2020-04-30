package com.tkdev.weatherapp.forecast.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.tkdev.weatherapp.current.data.retrofit_data_source.dto.Main
import com.tkdev.weatherapp.current.data.retrofit_data_source.dto.Sys
import com.tkdev.weatherapp.current.data.retrofit_data_source.dto.Weather
import com.tkdev.weatherapp.current.data.retrofit_data_source.dto.Wind

data class List (
        @SerializedName("dt")
    @Expose
    var dt: Int,

        @SerializedName("main")
    @Expose
    var main: Main,

        @SerializedName("weather")
    @Expose
    var weather: kotlin.collections.List<Weather>,

        @SerializedName("clouds")
    @Expose
    var clouds: Clouds,

        @SerializedName("wind")
    @Expose
    var wind: Wind,

        @SerializedName("rain")
    @Expose
    var rain: Rain,

        @SerializedName("sys")
    @Expose
    var sys: Sys,

        @SerializedName("dt_txt")
    @Expose
    var dtTxt: String,

        @SerializedName("snow")
    @Expose
    var snow: Snow
    )