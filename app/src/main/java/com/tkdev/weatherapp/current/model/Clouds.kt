package com.tkdev.weatherapp.current.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Clouds (
    @SerializedName("all")
    @Expose
    private var all: Int)