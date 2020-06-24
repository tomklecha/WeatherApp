package com.tkdev.weatherapp.common.domain.retrofit_data_source.forecast_dto

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