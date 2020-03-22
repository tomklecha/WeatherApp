package com.tkdev.weatherapp.model.forecast_weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ForecastRetrofit {
    @SerializedName("cod")
    @Expose
    var cod: String = ""

    @SerializedName("message")
    @Expose
    var message: Int? = null

    @SerializedName("cnt")
    @Expose
    var cnt: Int? = null

    @SerializedName("list")
    @Expose
    lateinit var list: kotlin.collections.List<List>

    @SerializedName("city")
    @Expose
    lateinit var city: City

    fun withCod(cod: String): ForecastRetrofit {
        this.cod = cod
        return this
    }

    fun withMessage(message: Int?): ForecastRetrofit {
        this.message = message
        return this
    }

    fun withCnt(cnt: Int?): ForecastRetrofit {
        this.cnt = cnt
        return this
    }

    fun withList(list: kotlin.collections.List<List>): ForecastRetrofit {
        this.list = list
        return this
    }

    fun withCity(city: City): ForecastRetrofit {
        this.city = city
        return this
    }
}