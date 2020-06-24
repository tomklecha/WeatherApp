package com.tkdev.weatherapp.common.domain.retrofit_data_source.forecast_dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.tkdev.weatherapp.common.domain.retrofit_data_source.current_dto.Main
import com.tkdev.weatherapp.common.domain.retrofit_data_source.current_dto.Sys
import com.tkdev.weatherapp.common.domain.retrofit_data_source.current_dto.Weather
import com.tkdev.weatherapp.common.domain.retrofit_data_source.current_dto.Wind

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