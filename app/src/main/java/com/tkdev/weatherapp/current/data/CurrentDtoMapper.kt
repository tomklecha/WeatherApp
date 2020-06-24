package com.tkdev.weatherapp.current.data

import com.tkdev.weatherapp.current.core.*
import com.tkdev.weatherapp.common.domain.retrofit_data_source.current_dto.CurrentRetrofit
import retrofit2.Response

interface CurrentDtoMapper {
    fun toDomain(response: Response<CurrentRetrofit>, prefix: String): WeatherDomain
}

class CurrentDtoMapperDefault : CurrentDtoMapper {
    override fun toDomain(response: Response<CurrentRetrofit>, prefix: String): WeatherDomain =
            if (response.code() > 400) {
                WeatherDomain.Fail(WeatherErrorDomain("Domain response failed"))
            } else responseToDomain(response.body()!!, prefix)

    private fun responseToDomain(body: CurrentRetrofit, prefix: String): WeatherDomain = WeatherDomain.Weather(
            WeatherDomainCity(body.name),
            WeatherDomainTempObject(
                    WeatherDomainTemp(body.main.temp),
                    WeatherDomainTempMin(body.main.tempMin),
                    WeatherDomainTempMax(body.main.tempMax),
                    WeatherDomainTempPrefix(prefix)
            ),
            WeatherDomainHumidity(body.main.humidity),
            WeatherDomainDescription(body.weather[0].description),
            WeatherDomainLastUpdate(body.dt),
            WeatherDomainTimezone(body.timezone),
            WeatherDomainWeatherIcon(body.weather[0].icon)
    )

}

