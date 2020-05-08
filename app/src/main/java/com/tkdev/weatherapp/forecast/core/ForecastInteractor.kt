package com.tkdev.weatherapp.forecast.core

class ForecastInteractor(private val repository: ForecastContract.Repository
) : ForecastContract.Interactor {


    override suspend fun getForecasts(city: ForecastDomainCity): ForecastDomain {
        return repository.apiRequest(city)
    }
}