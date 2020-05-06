package com.tkdev.weatherapp.current.core

sealed class WeatherDomain {
    data class Fail(val errorDomain: WeatherErrorDomain): WeatherDomain()
    data class Weather(
            val city: WeatherDomainCity,
            val tempObject: WeatherDomainTempObject,
            val humidity: WeatherDomainHumidity,
            val description: WeatherDomainDescription,
            val lastUpdate: WeatherDomainLastUpdate,
            val timezone: WeatherDomainTimezone
    ) : WeatherDomain()
}

data class WeatherDomainTempObject(
        val temp: WeatherDomainTemp,
        val tempMin: WeatherDomainTempMin,
        val tempMax: WeatherDomainTempMax,
        val prefix: WeatherDomainTempPrefix
)

inline class WeatherDomainCity(val value: String)
inline class WeatherDomainTemp(val value: Double)
inline class WeatherDomainTempMin(val value: Double)
inline class WeatherDomainTempMax(val value: Double)
inline class WeatherDomainTempPrefix(val value: String)
inline class WeatherDomainHumidity(val value: Int)
inline class WeatherDomainDescription(val value: String)
inline class WeatherDomainLastUpdate(val value: Int)
inline class WeatherErrorDomain(val value: String)
inline class WeatherDomainTimezone(val value: Int)