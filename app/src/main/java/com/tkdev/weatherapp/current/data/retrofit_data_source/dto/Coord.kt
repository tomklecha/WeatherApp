package com.tkdev.weatherapp.current.data.retrofit_data_source.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Coord(
        @SerializedName("lon")
        @Expose
        var lon: Double,

        @SerializedName("lat")
        @Expose
        var lat: Double)