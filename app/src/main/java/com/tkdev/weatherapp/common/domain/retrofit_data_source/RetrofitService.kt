package com.tkdev.weatherapp.common.domain.retrofit_data_source

import com.tkdev.weatherapp.common.domain.RetrofitCalls
import com.tkdev.weatherapp.common.domain.RetrofitCalls.Companion.WEATHER_API_KEY
import com.tkdev.weatherapp.common.domain.RetrofitCalls.Companion.WEATHER_API_PREFIX
import com.tkdev.weatherapp.common.domain.RetrofitCalls.Companion.WEATHER_CITY_NAME
import com.tkdev.weatherapp.common.domain.RetrofitCalls.Companion.WEATHER_FORECAST_REQUEST
import com.tkdev.weatherapp.common.domain.RetrofitCalls.Companion.WEATHER_TEMPERATURE_PREFIX
import com.tkdev.weatherapp.common.domain.retrofit_data_source.forecast_dto.ForecastRetrofit
import com.tkdev.weatherapp.common.domain.retrofit_data_source.current_dto.CurrentRetrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET(RetrofitCalls.WEATHER_CURRENT_REQUEST + WEATHER_API_PREFIX + WEATHER_API_KEY)
    fun getCurrentWeather(@Query(WEATHER_CITY_NAME) city: String,
                          @Query(WEATHER_TEMPERATURE_PREFIX) prefix: String): Call<CurrentRetrofit>
    @GET(WEATHER_FORECAST_REQUEST + WEATHER_API_PREFIX + WEATHER_API_KEY)
    fun getForecastWeather(@Query(WEATHER_CITY_NAME) city: String,
                           @Query(WEATHER_TEMPERATURE_PREFIX) prefix: String): Call<ForecastRetrofit>

}