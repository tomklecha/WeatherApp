package com.tkdev.weatherapp.forecast.core

sealed class ForecastDomain {
    data class Fail(val errorDomain: ForecastErrorDomain): ForecastDomain()
    data class WeatherForecast(
            val city: ForecastDomainCity,
            val timezone: ForecastDomainTimezone,
            val listForecasts: ForecastDomainList
    ) : ForecastDomain()
}

data class ForecastDomainList(
        val lastUpdate: ForecastDomainLastUpdate,
        val tempObject: ForecastDomainTempObject,
        val humidity: ForecastDomainHumidity,
        val weather: ForecastDomainWeatherObject
)

data class ForecastDomainTempObject(
        val temp: ForecastDomainTemp,
        val tempMin: ForecastDomainTempMin,
        val tempMax: ForecastDomainTempMax,
        val prefix: ForecastDomainTempPrefix
)

data class ForecastDomainWeatherObject(
    val description: ForecastDomainDescription,
    val icon: ForecastDomainIcon
)

inline class ForecastDomainCity(val value: String)
inline class ForecastDomainTimezone(val value: Int)
inline class ForecastDomainTemp(val value: Double)
inline class ForecastDomainTempMin(val value: Double)
inline class ForecastDomainTempMax(val value: Double)
inline class ForecastDomainTempPrefix(val value: String)
inline class ForecastDomainHumidity(val value: Int)
inline class ForecastDomainDescription(val value: String)
inline class ForecastDomainIcon(val value: String)
inline class ForecastDomainLastUpdate(val value: Long)
inline class ForecastErrorDomain(val value: String)