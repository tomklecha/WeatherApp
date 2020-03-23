package com.tkdev.weatherapp.current.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherRetrofit {
    @SerializedName("coord")
    @Expose
    var coord: Coord? = null

    @SerializedName("weather")
    @Expose
    var weather: List<Weather>? = null

    @SerializedName("base")
    @Expose
    var base: String? = null

    @SerializedName("main")
    @Expose
    var main: Main? = null

    @SerializedName("visibility")
    @Expose
    var visibility: Int? = null

    @SerializedName("wind")
    @Expose
    var wind: Wind? = null

    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null

    @SerializedName("dt")
    @Expose
    var dt: Int? = null

    @SerializedName("sys")
    @Expose
    var sys: Sys? = null

    @SerializedName("timezone")
    @Expose
    var timezone: Int? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("cod")
    @Expose
    var cod: Int? = null

    fun withCoord(coord: Coord?): WeatherRetrofit {
        this.coord = coord
        return this
    }

    fun withWeather(weather: List<Weather>?): WeatherRetrofit {
        this.weather = weather
        return this
    }

    fun withBase(base: String?): WeatherRetrofit {
        this.base = base
        return this
    }

    fun withMain(main: Main?): WeatherRetrofit {
        this.main = main
        return this
    }

    fun withVisibility(visibility: Int?): WeatherRetrofit {
        this.visibility = visibility
        return this
    }

    fun withWind(wind: Wind?): WeatherRetrofit {
        this.wind = wind
        return this
    }

    fun withClouds(clouds: Clouds?): WeatherRetrofit {
        this.clouds = clouds
        return this
    }

    fun withDt(dt: Int?): WeatherRetrofit {
        this.dt = dt
        return this
    }

    fun withSys(sys: Sys?): WeatherRetrofit {
        this.sys = sys
        return this
    }

    fun withTimezone(timezone: Int?): WeatherRetrofit {
        this.timezone = timezone
        return this
    }

    fun withId(id: Int?): WeatherRetrofit {
        this.id = id
        return this
    }

    fun withName(name: String?): WeatherRetrofit {
        this.name = name
        return this
    }

    fun withCod(cod: Int?): WeatherRetrofit {
        this.cod = cod
        return this
    }
}