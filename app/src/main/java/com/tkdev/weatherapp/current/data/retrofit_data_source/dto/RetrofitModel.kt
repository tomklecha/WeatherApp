package com.tkdev.weatherapp.current.data.retrofit_data_source.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RetrofitModel(
        @SerializedName("coord")
        @Expose
        var coord: Coord,

        @SerializedName("weather")
        @Expose
        var weather: List<Weather>,

        @SerializedName("base")
        @Expose
        var base: String,

        @SerializedName("main")
        @Expose
        var main: Main,

        @SerializedName("visibility")
        @Expose
        var visibility: Int,

        @SerializedName("wind")
        @Expose
        var wind: Wind,

        @SerializedName("clouds")
        @Expose
        var clouds: Clouds,

        @SerializedName("dt")
        @Expose
        var dt: Int,

        @SerializedName("sys")
        @Expose
        var sys: Sys,

        @SerializedName("timezone")
        @Expose
        var timezone: Int,

        @SerializedName("id")
        @Expose
        var id: Int,

        @SerializedName("name")
        @Expose
        var name: String,

        @SerializedName("cod")
        @Expose
        var cod: Int)