package com.tkdev.weatherapp.current.core

sealed class CurrentWeatherDomain {
    object Success: CurrentWeatherDomain()
    data class Fail(val errorDomain: CurrentWeatherErrorDomain): CurrentWeatherDomain()
    data class Weather(
            val city: CurrentWeatherDomainCity,
            val temp: CurrentWeatherDomainTemp,
            val tempMin: CurrentWeatherDomainTempMin,
            val tempMax: CurrentWeatherDomainTempMax,
            val humidity: CurrentWeatherDomainHumidity,
            val description: CurrentWeatherDomainDescription
    ) : CurrentWeatherDomain()
}

inline class CurrentWeatherDomainCity(val value: String)
inline class CurrentWeatherDomainTemp(val value: String)
inline class CurrentWeatherDomainTempMin(val value: String)
inline class CurrentWeatherDomainTempMax(val value: String)
inline class CurrentWeatherDomainHumidity(val value: String)
inline class CurrentWeatherDomainDescription(val value: String)
inline class CurrentWeatherErrorDomain(val value: String)