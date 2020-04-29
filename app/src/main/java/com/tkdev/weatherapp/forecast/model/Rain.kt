package com.tkdev.weatherapp.forecast.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Rain (
    @SerializedName("3h")
    @Expose
    private var _3h: Double)