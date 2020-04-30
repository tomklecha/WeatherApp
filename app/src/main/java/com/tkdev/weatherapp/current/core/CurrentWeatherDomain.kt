package com.tkdev.weatherapp.current.core

sealed class CurrentWeatherDomain {
    object Success: CurrentWeatherDomain()
    data class Fail(val errorDomain: CurrentWeatherErrorDomain): CurrentWeatherDomain()
}

 inline class CurrentWeatherDomainTemp(val value: String)
 inline class CurrentWeatherDomainCity(val value: String)
 inline class CurrentWeatherErrorDomain(val value: String)