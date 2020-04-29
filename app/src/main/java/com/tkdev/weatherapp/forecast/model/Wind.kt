package com.tkdev.weatherapp.forecast.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Wind (
    @SerializedName("speed")
    @Expose
    var speed: Double,

    @SerializedName("deg")
    @Expose
    var deg: Int)
