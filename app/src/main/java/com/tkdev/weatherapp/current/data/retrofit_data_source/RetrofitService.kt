package com.tkdev.weatherapp.current.data.retrofit_data_source

import com.tkdev.weatherapp.common.domain.RetrofitCalls.Companion.AND_SYMBOL
import com.tkdev.weatherapp.common.domain.RetrofitCalls.Companion.WEATHER_API_KEY
import com.tkdev.weatherapp.common.domain.RetrofitCalls.Companion.WEATHER_API_PREFIX
import com.tkdev.weatherapp.common.domain.RetrofitCalls.Companion.WEATHER_CITY_NAME
import com.tkdev.weatherapp.common.domain.RetrofitCalls.Companion.WEATHER_CURRENT_REQUEST
import com.tkdev.weatherapp.common.domain.RetrofitCalls.Companion.WEATHER_TEMPERATURE_CELSIUS
import com.tkdev.weatherapp.common.domain.RetrofitCalls.Companion.WEATHER_TEMPERATURE_PREFIX
import com.tkdev.weatherapp.current.data.retrofit_data_source.dto.RetrofitModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET(WEATHER_CURRENT_REQUEST + WEATHER_API_PREFIX + WEATHER_API_KEY +
            AND_SYMBOL + WEATHER_TEMPERATURE_PREFIX + WEATHER_TEMPERATURE_CELSIUS)
    fun getCurrentWeather(@Query(WEATHER_CITY_NAME) city: String): Call<RetrofitModel>

}