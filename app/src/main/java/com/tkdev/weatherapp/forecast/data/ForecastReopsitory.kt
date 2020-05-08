package com.tkdev.weatherapp.forecast.data

import com.tkdev.weatherapp.common.domain.retrofit_data_source.RetrofitForecastApi
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.current_prefix
import com.tkdev.weatherapp.forecast.core.ForecastContract
import com.tkdev.weatherapp.forecast.core.ForecastDomain
import com.tkdev.weatherapp.forecast.core.ForecastDomainCity
import com.tkdev.weatherapp.forecast.core.ForecastErrorDomain

class ForecastReopsitory(
        private val api: RetrofitForecastApi,
        private val dto: ForecastDtoMapper
) : ForecastContract.Repository {

    override suspend fun apiRequest(city: ForecastDomainCity): ForecastDomain = try {
        dto.toDomain(api.getForecastWeather(city.value, current_prefix), current_prefix)
    } catch (e: Exception) {
        ForecastDomain.Fail(ForecastErrorDomain(e.message.toString()))
    }
}


