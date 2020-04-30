package com.tkdev.weatherapp.current.data.retrofit_data_source.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Sys (
    @SerializedName("type")
    @Expose
    var type: Int,

    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("country")
    @Expose
    var country: String,

    @SerializedName("sunrise")
    @Expose
    var sunrise: Int,

    @SerializedName("sunset")
    @Expose
    var sunset: Int)