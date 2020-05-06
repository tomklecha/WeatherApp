package com.tkdev.weatherapp.forecast.data

import com.tkdev.weatherapp.forecast.core.*
import com.tkdev.weatherapp.common.domain.retrofit_data_source.RetrofitForecastApi

class ForecastReopsitory(
        private val api: RetrofitForecastApi,
        private val dto: ForecastDtoMapper
)
    : ForecastContract.Repository {

    override suspend fun apiRequest(city: ForecastDomainCity, prefix: ForecastDomainTempPrefix): ForecastDomain = try {
        dto.toDomain(api.getForecastWeather(city.value, prefix.value), prefix.value)
    } catch (e: Exception) {
        ForecastDomain.Fail(ForecastErrorDomain(e.message.toString()))
    }
}


