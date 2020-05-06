package com.tkdev.weatherapp.current.data

import com.tkdev.weatherapp.common.domain.retrofit_data_source.RetrofitCurrentApi
import com.tkdev.weatherapp.current.core.*

class CurrentRepository(
        private val api: RetrofitCurrentApi,
        private val dto: CurrentDtoMapper)
    : CurrentContract.Repository {

    override suspend fun apiRequest(city: WeatherDomainCity, prefix: WeatherDomainTempPrefix): WeatherDomain = try {
        dto.toDomain(api.getCurrentWeather(city.value, prefix.value), prefix.value)
    } catch (e: Exception) {
        WeatherDomain.Fail(WeatherErrorDomain(e.message.toString()))
    }
}


