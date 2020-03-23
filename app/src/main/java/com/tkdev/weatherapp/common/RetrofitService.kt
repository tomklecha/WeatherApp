package com.tkdev.weatherapp.common

import com.tkdev.weatherapp.current.model.WeatherRetrofit
import com.tkdev.weatherapp.forecast.model.ForecastRetrofit
import com.tkdev.weatherapp.common.RetrofitCalls.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET(WEATHER_CURRENT_REQUEST + WEATHER_API_PREFIX + WEATHER_API_KEY +
            AND_SYMBOL + WEATHER_TEMPERATURE_PREFIX + WEATHER_TEMPERATURE_CELSIUS)
    fun getCurrentWeather(@Query(WEATHER_CITY_NAME) city: String): Call<WeatherRetrofit>

    @GET(WEATHER_FORECAST_REQUEST + WEATHER_API_PREFIX + WEATHER_API_KEY +
            AND_SYMBOL + WEATHER_TEMPERATURE_PREFIX + WEATHER_TEMPERATURE_CELSIUS)
    fun getForecastWeather(@Query(WEATHER_CITY_NAME) city: String): Call<ForecastRetrofit>
}