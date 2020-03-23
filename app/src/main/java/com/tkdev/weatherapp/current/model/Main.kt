package com.tkdev.weatherapp.current.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Main {
    @SerializedName("temp")
    @Expose
    var temp: Double? = null

    @SerializedName("feels_like")
    @Expose
    var feelsLike: Double? = null

    @SerializedName("temp_min")
    @Expose
    var tempMin: Double? = null

    @SerializedName("temp_max")
    @Expose
    var tempMax: Double? = null

    @SerializedName("pressure")
    @Expose
    var pressure: Int? = null

    @SerializedName("humidity")
    @Expose
    var humidity: Int? = null

    fun withTemp(temp: Double?): Main {
        this.temp = temp
        return this
    }

    fun withFeelsLike(feelsLike: Double?): Main {
        this.feelsLike = feelsLike
        return this
    }

    fun withTempMin(tempMin: Double?): Main {
        this.tempMin = tempMin
        return this
    }

    fun withTempMax(tempMax: Double?): Main {
        this.tempMax = tempMax
        return this
    }

    fun withPressure(pressure: Int?): Main {
        this.pressure = pressure
        return this
    }

    fun withHumidity(humidity: Int?): Main {
        this.humidity = humidity
        return this
    }
}