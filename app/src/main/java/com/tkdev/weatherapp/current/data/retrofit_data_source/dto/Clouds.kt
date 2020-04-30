package com.tkdev.weatherapp.current.data.retrofit_data_source.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Clouds (
    @SerializedName("all")
    @Expose
    private var all: Int)