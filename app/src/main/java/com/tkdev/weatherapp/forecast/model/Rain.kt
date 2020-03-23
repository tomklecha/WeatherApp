package com.tkdev.weatherapp.forecast.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Rain {
    @SerializedName("3h")
    @Expose
    private var _3h: Double? = null
    fun get3h(): Double? {
        return _3h
    }

    fun set3h(_3h: Double?) {
        this._3h = _3h
    }

    fun with3h(_3h: Double?): Rain {
        this._3h = _3h
        return this
    }
}