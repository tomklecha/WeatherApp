package com.tkdev.weatherapp.current.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Weather {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("main")
    @Expose
    var main: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("icon")
    @Expose
    var icon: String? = null

    fun withId(id: Int?): Weather {
        this.id = id
        return this
    }

    fun withMain(main: String?): Weather {
        this.main = main
        return this
    }

    fun withDescription(description: String?): Weather {
        this.description = description
        return this
    }

    fun withIcon(icon: String?): Weather {
        this.icon = icon
        return this
    }
}