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
    }

    interface Presenter {
        fun onDestroy()

        fun bind(view: View)

        fun onRequestWeather(city: String)

        fun sendCurrentWeather(): String

        fun getWeatherIcon(imageView: ImageView)

        fun loadData()
    }

    interface Interactor {
        suspend fun getWeather(city: WeatherDomainCity): WeatherDomain

        fun loadData(): WeatherDomain.Weather

        fun saveCurrentWeather(weather: WeatherDomain.Weather) {

        }
    }

    interface Repository{
        suspend fun apiRequest(city: WeatherDomainCity) : WeatherDomain

        fun loadSharedPreferenceDomain(): WeatherDomain.Weather

        fun saveSharedPreferences(weather: WeatherDomain.Weather) {

        }
    }

}