package com.tkdev.weatherapp.current.core

class CurrentInteractor(
        private val repository: CurrentContract.Repository
) : CurrentContract.Interactor {


    override suspend fun getWeather(city: WeatherDomainCity, prefix: WeatherDomainTempPrefix): WeatherDomain {
        return repository.apiRequest(city, prefix)
    }
}

// TODO() implement interactions with API like -> city, lang, temp prefix

