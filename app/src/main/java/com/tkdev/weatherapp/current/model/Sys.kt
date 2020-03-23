package com.tkdev.weatherapp.current.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Sys {
    @SerializedName("type")
    @Expose
    var type: Int? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("sunrise")
    @Expose
    var sunrise: Int? = null

    @SerializedName("sunset")
    @Expose
    var sunset: Int? = null

    fun withType(type: Int?): Sys {
        this.type = type
        return this
    }

    fun withId(id: Int?): Sys {
        this.id = id
        return this
    }

    fun withCountry(country: String?): Sys {
        this.country = country
        return this
    }

    fun withSunrise(sunrise: Int?): Sys {
        this.sunrise = sunrise
        return this
    }

    fun withSunset(sunset: Int?): Sys {
        this.sunset = sunset
        return this
    }
}