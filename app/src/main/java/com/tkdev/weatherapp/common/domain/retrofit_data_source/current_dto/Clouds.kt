package com.tkdev.weatherapp.common.domain.retrofit_data_source.current_dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Clouds (
    @SerializedName("all")
    @Expose
    private var all: Int)