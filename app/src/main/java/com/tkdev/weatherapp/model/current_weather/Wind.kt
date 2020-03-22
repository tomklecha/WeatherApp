package com.tkdev.weatherapp.model.current_weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Wind {
    @SerializedName("speed")
    @Expose
    var speed: Double? = null

    @SerializedName("deg")
    @Expose
    var deg: Int? = null

    fun withSpeed(speed: Double?): Wind {
        this.speed = speed
        return this
    }

    fun withDeg(deg: Int?): Wind {
        this.deg = deg
        return this
    }
}