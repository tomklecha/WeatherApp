package com.tkdev.weatherapp.current.data

import com.tkdev.weatherapp.current.core.CurrentContract
import com.tkdev.weatherapp.current.core.CurrentWeatherDomain
import com.tkdev.weatherapp.current.core.CurrentWeatherDomainCity
import com.tkdev.weatherapp.current.core.CurrentWeatherErrorDomain
import com.tkdev.weatherapp.current.data.retrofit_data_source.RetrofitApi

class CurrentRepository(
        private val api: RetrofitApi,
        private val dto: DtoMapper)
    : CurrentContract.Repository {

    override suspend fun apiRequest(city: CurrentWeatherDomainCity): CurrentWeatherDomain = try {
        dto.toDomain(api.getCurrentWeather(city.value))
    } catch (e: Exception) {
        CurrentWeatherDomain.Fail(CurrentWeatherErrorDomain(e.message.toString()))
    }
}


