package com.tkdev.weatherapp.current.core

class CurrentInteractor(
        private val repository: CurrentContract.Repository
) : CurrentContract.Interactor {


    override suspend fun getWeather(city: WeatherDomainCity): WeatherDomain
   = repository.apiRequest(city)


    override fun loadData() : WeatherDomain.Weather = repository.loadSharedPreferenceDomain()

    override fun saveCurrentWeather(weather: WeatherDomain.Weather) = repository.saveSharedPreferences(weather)

}

// TODO() implement interactions with API like -> city, lang, temp prefix

