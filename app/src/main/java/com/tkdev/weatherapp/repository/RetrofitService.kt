package com.tkdev.weatherapp.repository

import com.tkdev.weatherapp.model.current_weather.WeatherRetrofit
import com.tkdev.weatherapp.model.forecast_weather.ForecastRetrofit
import com.tkdev.weatherapp.utils.RetrofitCalls.*
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