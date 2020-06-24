package com.tkdev.weatherapp.forecast.data

import com.tkdev.weatherapp.common.domain.retrofit_data_source.forecast_dto.ForecastRetrofit
import com.tkdev.weatherapp.forecast.core.*
import retrofit2.Response

interface ForecastDtoMapper {
    fun toDomain(response: Response<ForecastRetrofit>, prefix: String): ForecastDomain
}

class ForecastDtoMapperDefault : ForecastDtoMapper {

    override fun toDomain(response: Response<ForecastRetrofit>, prefix: String): ForecastDomain =
        if (response.code() > 400) {
            ForecastDomain.Fail(ForecastErrorDomain("Domain response failed"))
        }else    responseToDomain(response.body()!!, prefix)
    }

    private fun responseToDomain(body: ForecastRetrofit, prefix: String): ForecastDomain =
        ForecastDomain.WeatherForecast(
                body
        )










