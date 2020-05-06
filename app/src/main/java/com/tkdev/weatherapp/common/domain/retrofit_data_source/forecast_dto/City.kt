package com.tkdev.weatherapp.common.domain.retrofit_data_source.forecast_dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.tkdev.weatherapp.common.domain.retrofit_data_source.current_dto.Coord

data class City(
        @SerializedName("id")
        @Expose
        var id: Int,

        @SerializedName("name")
        @Expose
        var name: String,

        @SerializedName("coord")
        @Expose
        var coord: Coord,
        @SerializedName("country")
        @Expose
        var country: String,

        @SerializedName("timezone")
        @Expose
        var timezone: Int,

        @SerializedName("sunrise")
        @Expose
        var sunrise: Int,

        @SerializedName("sunset")
        @Expose
        var sunset: Int
)
