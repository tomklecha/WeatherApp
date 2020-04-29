package com.tkdev.weatherapp.forecast.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Coord(
        @SerializedName("lat")
        @Expose
        var lat: Double,

        @SerializedName("lon")
        @Expose
        var lon: Double
)