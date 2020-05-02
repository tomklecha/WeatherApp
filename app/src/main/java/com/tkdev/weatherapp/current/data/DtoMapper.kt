package com.tkdev.weatherapp.current.data

import com.tkdev.weatherapp.current.core.*
import com.tkdev.weatherapp.current.data.retrofit_data_source.dto.RetrofitModel
import retrofit2.Response

interface DtoMapper {
    fun toDomain(response: Response<RetrofitModel>, prefix: String): WeatherDomain
}

class DtoMapperDefault : DtoMapper {
    override fun toDomain(response: Response<RetrofitModel>, prefix: String): WeatherDomain =
            if (response.code() > 400) {
                WeatherDomain.Fail(WeatherErrorDomain("Domain response failed"))
            }else    responseToDomain(response.body()!!, prefix)

    private fun responseToDomain(body: RetrofitModel, prefix: String): WeatherDomain = WeatherDomain.Weather(
            WeatherDomainCity(body.name),
            WeatherDomainTempObject(
                    WeatherDomainTemp(body.main.temp),
                    WeatherDomainTempMin(body.main.tempMin),
                    WeatherDomainTempMax(body.main.tempMax),
                    WeatherDomainTempPrefix(prefix)
            ),
            WeatherDomainHumidity(body.main.humidity),
            WeatherDomainDescription(body.weather[0].description),
            WeatherDomainLastUpdate(body.dt.toLong()*1000)
    )

}

