package com.tkdev.weatherapp.current

import com.tkdev.weatherapp.common.MainContract

interface CurrentPresenter : MainContract {
    fun setTemperatureCurrentTextView(): String
    fun setTemperatureMinimumTextView(): String
    fun setTemperatureMaximumTextView(): String
    fun setWeatherDescriptionTextView(): String
    fun setHumidityViewText(): String
    fun setLastUpdateViewText(): String
    fun setDateViewText(): String
    fun setCityName(): String
    fun sendCurrentWeather(): String
}