package com.tkdev.weatherapp.current.bresenter.model

data class WeatherModel (
        val city: WeatherModelCity,
        val tempObject: WeatherModelTempObject,
        val humidity: WeatherModelHumidity,
        val description: WeatherModelDescription,
        val lastUpdate: WeatherModelLastUpdate
    )

data class WeatherModelTempObject(
        val temp: WeatherModelTempCurrent,
        val tempMin: WeatherModelTempMin,
        val tempMax: WeatherModelTempMax,
        val prefix: WeatherModelTempPrefix
)


inline class WeatherModelCity(val value: String)
inline class WeatherModelTempCurrent(val value: String)
inline class WeatherModelTempMin(val value: String)
inline class WeatherModelTempMax(val value: String)
inline class WeatherModelTempPrefix(val value: String)
inline class WeatherModelHumidity(val value: String)
inline class WeatherModelDescription(val value: String)
inline class WeatherModelLastUpdate(val value: String)