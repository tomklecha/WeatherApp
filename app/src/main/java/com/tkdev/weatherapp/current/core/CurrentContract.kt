package com.tkdev.weatherapp.current.core

import android.widget.ImageView
import java.util.*

interface CurrentContract {

    interface View {
        fun showWeatherByCity(city: String, prefix: String)

        fun onFailUpdate(message: String)

        fun setTemperatureCurrent(value: String)

        fun setTemperatureMinimum(value: String)

        fun setTemperatureMaximum(value: String)

        fun setWeatherDescription(value: String)

        fun setHumidity(value: String)

        fun setLastUpdate(value: String)

        fun setCityName(value: String)
    }

    interface Presenter {
        fun onDestroy()

        fun bind(view: View)

        fun onRequestWeather(city: String, prefix: String)

        fun sendCurrentWeather(): String

        fun getWeatherIcon(imageView: ImageView)
    }

    interface Interactor {
        suspend fun getWeather(city: WeatherDomainCity, prefix: WeatherDomainTempPrefix): WeatherDomain
    }

    interface Repository{
        suspend fun apiRequest(city: WeatherDomainCity, prefix: WeatherDomainTempPrefix) : WeatherDomain
    }

}