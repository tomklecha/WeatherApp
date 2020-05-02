package com.tkdev.weatherapp.current.data.retrofit_data_source

import com.tkdev.weatherapp.common.domain.RetrofitCalls
import com.tkdev.weatherapp.current.data.retrofit_data_source.dto.RetrofitModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory


interface RetrofitApi {
    suspend fun getCurrentWeather(city: String, prefix: String) : Response<RetrofitModel>
}

class RetrofitApiDefault : RetrofitApi {

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

    override suspend fun getCurrentWeather(city: String, prefix: String): Response<RetrofitModel> =
//             service.getCurrentWeather(city).awaitResponse()
             service.getCurrentWeather(city,prefix).awaitResponse()

}