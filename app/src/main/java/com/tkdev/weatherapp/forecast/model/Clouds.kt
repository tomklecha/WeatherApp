package com.tkdev.weatherapp.forecast.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Clouds(
        @SerializedName("all")
        @Expose
        var all: Int)