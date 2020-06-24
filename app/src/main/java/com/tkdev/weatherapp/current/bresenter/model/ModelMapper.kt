package com.tkdev.weatherapp.current.bresenter.model

import com.tkdev.weatherapp.common.domain.RetrofitCalls.Companion.LAST_UPDATE_PATTERN
import com.tkdev.weatherapp.common.domain.RetrofitCalls.Companion.datePattern
import com.tkdev.weatherapp.common.domain.RetrofitCalls.Companion.humidityPrefix
import com.tkdev.weatherapp.common.domain.RetrofitCalls.Companion.temperaturePrefix
import com.tkdev.weatherapp.current.core.WeatherDomain

interface ModelMapper {
    fun toModel(domain: WeatherDomain.Weather): WeatherModel
}

class ModelMapperDefault : ModelMapper {
    override fun toModel(domain: WeatherDomain.Weather): WeatherModel =
            mapToModel(domain)

    private fun mapToModel(domain: WeatherDomain.Weather): WeatherModel = WeatherModel(
            WeatherModelCity(domain.city.value),
            WeatherModelTempObject(
                    WeatherModelTempCurrent(temperaturePrefix(domain.tempObject.temp.value, domain.tempObject.prefix.value)),
                    WeatherModelTempMin(temperaturePrefix(domain.tempObject.tempMin.value, domain.tempObject.prefix.value)),
                    WeatherModelTempMax(temperaturePrefix(domain.tempObject.tempMax.value, domain.tempObject.prefix.value)),
                    WeatherModelTempPrefix(domain.tempObject.prefix.value)
            ),
            WeatherModelHumidity(humidityPrefix(domain.humidity.value)),
            WeatherModelDescription(domain.description.value),
            WeatherModelLastUpdate(datePattern(domain.lastUpdate.value,domain.timezone.value, LAST_UPDATE_PATTERN)),
            WeatherModelIcon(domain.icon.value)
    )
}
