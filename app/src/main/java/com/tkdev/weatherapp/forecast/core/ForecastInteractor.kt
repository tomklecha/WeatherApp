package com.tkdev.weatherapp.forecast.core

import com.tkdev.weatherapp.current.core.WeatherDomain

class ForecastInteractor(private val repository: ForecastContract.Repository
) : ForecastContract.Interactor {

    override suspend fun getForecasts(city: ForecastDomainCity): ForecastDomain = repository.apiRequest(city)

    override suspend fun loadData() : ForecastDomain = repository.loadSharedPreferenceDomain()

    override fun saveCurrentWeather(forecast: ForecastDomain.WeatherForecast) = repository.saveSharedPreferences(forecast)


}