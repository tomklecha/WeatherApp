package com.tkdev.weatherapp.forecast.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.tkdev.weatherapp.current.model.Coord

class City {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("coord")
    @Expose
    var coord: Coord? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("timezone")
    @Expose
    var timezone: Int = 0

    @SerializedName("sunrise")
    @Expose
    var sunrise: Int? = null

    @SerializedName("sunset")
    @Expose
    var sunset: Int? = null

    fun withId(id: Int?): City {
        this.id = id
        return this
    }

    fun withName(name: String?): City {
        this.name = name
        return this
    }

    fun withCoord(coord: Coord?): City {
        this.coord = coord
        return this
    }

    fun withCountry(country: String?): City {
        this.country = country
        return this
    }

    fun withTimezone(timezone: Int): City {
        this.timezone = timezone
        return this
    }

    fun withSunrise(sunrise: Int?): City {
        this.sunrise = sunrise
        return this
    }

    fun withSunset(sunset: Int?): City {
        this.sunset = sunset
        return this
    }
}