package com.tkdev.weatherapp.model.forecast_weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Coord {
    @SerializedName("lat")
    @Expose
    var lat: Double? = null

    @SerializedName("lon")
    @Expose
    var lon: Double? = null

    fun withLat(lat: Double?): Coord {
        this.lat = lat
        return this
    }

    fun withLon(lon: Double?): Coord {
        this.lon = lon
        return this
    }
}