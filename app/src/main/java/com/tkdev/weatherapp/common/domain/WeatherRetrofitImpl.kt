package com.tkdev.weatherapp.common.domain

import com.tkdev.weatherapp.common.core.MainContract
import com.tkdev.weatherapp.current.data.retrofit_data_source.dto.RetrofitModel
import com.tkdev.weatherapp.forecast.model.ForecastRetrofit
import com.tkdev.weatherapp.common.core.MainContract.APIForecastListener
import com.tkdev.weatherapp.common.core.MainContract.APIListener
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRetrofitImpl : MainContract.Model {
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

    override fun getWeather(listener: APIListener, city: String) {

        val call = service.getCurrentWeather(city)
        call.enqueue(object : Callback<RetrofitModel> {
            override fun onResponse(call: Call<RetrofitModel>, response: Response<RetrofitModel>) {
                if (response.body() != null && response.isSuccessful) {
                    listener.onSuccessResponse(response)
                } else {
                    listener.onFailureResponse(response.message())
                }
            }

            override fun onFailure(call: Call<RetrofitModel>, t: Throwable) {
                t.message?.let { listener.onFailureResponse(it) }
            }
        })
    }

    override fun getWeather(listener: APIForecastListener, city: String) {

        val call = service.getForecastWeather(city)
        call.enqueue(object : Callback<ForecastRetrofit> {
            override fun onResponse(call: Call<ForecastRetrofit>, response: Response<ForecastRetrofit>) {
                if (response.body() != null && response.isSuccessful) {
                    listener.onSuccessResponse(response)
                } else {
                    listener.onFailureResponse(response.message())
                }
            }

            override fun onFailure(call: Call<ForecastRetrofit>, t: Throwable) {}
        })
    }
}