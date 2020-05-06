package com.tkdev.weatherapp.common.domain.retrofit_data_source

import com.tkdev.weatherapp.common.domain.RetrofitCalls
import com.tkdev.weatherapp.common.domain.retrofit_data_source.forecast_dto.ForecastRetrofit
import com.tkdev.weatherapp.common.domain.retrofit_data_source.current_dto.CurrentRetrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitForecastApi {
    suspend fun getForecastWeather(city: String, prefix: String): Response<ForecastRetrofit>
}

interface RetrofitCurrentApi {
    suspend fun getCurrentWeather(city: String, prefix: String): Response<CurrentRetrofit>
}

class RetrofitApiDefault : RetrofitCurrentApi, RetrofitForecastApi {

    private var service: RetrofitService

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(RetrofitCalls.WEATHER_REQUEST_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        service = retrofit.create(RetrofitService::class.java)
    }

    override suspend fun getCurrentWeather(city: String, prefix: String): Response<CurrentRetrofit> =
            service.getCurrentWeather(city, prefix).awaitResponse()

    override suspend fun getForecastWeather(city: String, prefix: String): Response<ForecastRetrofit> =
            service.getForecastWeather(city, prefix).awaitResponse()
}