package com.tkdev.weatherapp.current.core

import android.widget.ImageView

interface CurrentContract {

    interface View {
        fun showWeatherByCity(city: String)

        fun onFailUpdate(message: String)

        fun setTemperatureCurrent(value: String)

        fun setTemperatureMinimum(value: String)

        fun setTemperatureMaximum(value: String)

        fun setWeatherDescription(value: String)

        fun setHumidity(value: String)

        fun setLastUpdate(value: String)

        fun setCityName(value: String)

        fun setImageIcon(icon: String)

    }

    interface Presenter {
        fun onDestroy()

        fun bind(view: View)

        fun showData()

        fun onRequestWeather(city: String)

        fun sendCurrentWeather(): String

        fun getWeatherIcon(imageView: ImageView)
    }

    interface Interactor {
        suspend fun getWeather(city: WeatherDomainCity): WeatherDomain

        suspend fun loadData(): WeatherDomain

        suspend fun saveCurrentWeather(weather: WeatherDomain.Weather)
    }

    interface Repository{
        suspend fun apiRequest(city: WeatherDomainCity) : WeatherDomain

        suspend fun loadSharedPreferenceDomain(): WeatherDomain

        suspend fun saveSharedPreferences(weather: WeatherDomain.Weather)
    }

}