package com.tkdev.weatherapp.model.current_weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Clouds {
    @SerializedName("all")
    @Expose
    var all: Int? = null

    fun withAll(all: Int?): Clouds {
        this.all = all
        return this
    }
}