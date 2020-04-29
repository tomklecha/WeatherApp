package com.tkdev.weatherapp.forecast.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ForecastRetrofit(
        @SerializedName("cod")
        @Expose
        var cod: String,

        @SerializedName("message")
        @Expose
        var message: Int,

        @SerializedName("cnt")
        @Expose
        var cnt: Int,

        @SerializedName("list")
        @Expose
        var list: kotlin.collections.List<List>,

        @SerializedName("city")
        @Expose
        var city: City
)