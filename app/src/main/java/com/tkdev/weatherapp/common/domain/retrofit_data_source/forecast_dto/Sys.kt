package com.tkdev.weatherapp.common.domain.retrofit_data_source.forecast_dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Sys (
    @SerializedName("pod")
    @Expose
    var pod: String)