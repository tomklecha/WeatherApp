package com.tkdev.weatherapp.model.forecast_weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Sys {
    @SerializedName("pod")
    @Expose
    var pod: String? = null

    fun withPod(pod: String?): Sys {
        this.pod = pod
        return this
    }
}