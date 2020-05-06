package com.tkdev.weatherapp.common.domain.retrofit_data_source.current_dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Wind (
    @SerializedName("speed")
    @Expose
    var speed: Double,

    @SerializedName("deg")
    @Expose
    var deg: Int)