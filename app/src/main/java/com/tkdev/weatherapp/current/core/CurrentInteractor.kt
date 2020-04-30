package com.tkdev.weatherapp.current.core

import com.tkdev.weatherapp.common.domain.RetrofitCalls
import com.tkdev.weatherapp.common.util.PreferencesVariables
import com.tkdev.weatherapp.current.data.retrofit_data_source.dto.RetrofitModel

class CurrentInteractor(
        private val repository: CurrentContract.Repository
) : CurrentContract.Interactor, CurrentContract.DomainListener {

    private lateinit var weather: RetrofitModel


    override fun getWeather(city: CurrentWeatherDomainCity) {
        repository.apiRequest(city)
    }

    override fun setTemperatureCurrent(): String = RetrofitCalls.temperaturePrefix(weather.main.temp)

    override fun setTemperatureMinimum(): String = RetrofitCalls.temperaturePrefix(weather.main.tempMin)

    override fun setTemperatureMaximum(): String = RetrofitCalls.temperaturePrefix(weather.main.tempMax)

    override fun setWeatherDescription(): String = weather.weather[0].main

    override fun setHumidity(): String = weather.main.humidity.toString() + RetrofitCalls.HUMIDITY_SYMBOL

    override fun setLastUpdate(): String = RetrofitCalls.datePattern(
            (weather.dt + weather.timezone - PreferencesVariables.summer_time).toLong()
                    * 1000, RetrofitCalls.LAST_UPDATE_PATTERN)

    override fun setDate(): String = RetrofitCalls.datePattern((System.currentTimeMillis()), RetrofitCalls.DATE_PATTERN)

    override fun setIcon(): String = weather.weather[0].icon

    override fun setCityName(): String = weather.name

    override fun sendCurrentWeather(): String {
        TODO("Not yet implemented")
    }

    override fun getApiResponse(response: RetrofitModel) {
         this.weather = response
    }


}