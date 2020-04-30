package com.tkdev.weatherapp.current.data

import com.tkdev.weatherapp.common.domain.RetrofitCalls
import com.tkdev.weatherapp.current.core.*
import com.tkdev.weatherapp.current.data.retrofit_data_source.dto.RetrofitModel
import retrofit2.Response

interface DtoMapper {
    fun toDomain(response: Response<RetrofitModel>): CurrentWeatherDomain
}

class DtoMapperDefault : DtoMapper {
    override fun toDomain(response: Response<RetrofitModel>): CurrentWeatherDomain = reMaptoDomain(response.body()!!)

    private fun reMaptoDomain(body: RetrofitModel): CurrentWeatherDomain = CurrentWeatherDomain.Weather(
            CurrentWeatherDomainCity(body.name),
            CurrentWeatherDomainTemp(RetrofitCalls.temperaturePrefix(body.main.temp)),
            CurrentWeatherDomainTempMin(RetrofitCalls.temperaturePrefix(body.main.tempMin)),
            CurrentWeatherDomainTempMax(RetrofitCalls.temperaturePrefix(body.main.tempMax)),
            CurrentWeatherDomainHumidity(RetrofitCalls.humidityPrefix(body.main.humidity)),
            CurrentWeatherDomainDescription(body.weather[0].description)
    )

}

