package com.tkdev.weatherapp.repository

import com.tkdev.weatherapp.model.current_weather.WeatherRetrofit
import com.tkdev.weatherapp.model.forecast_weather.ForecastRetrofit
import com.tkdev.weatherapp.presenter.MainContract
import com.tkdev.weatherapp.presenter.MainContract.APIForecastListener
import com.tkdev.weatherapp.presenter.MainContract.APIListener
import com.tkdev.weatherapp.utils.RetrofitCalls
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
        call.enqueue(object : Callback<WeatherRetrofit> {
            override fun onResponse(call: Call<WeatherRetrofit>, response: Response<WeatherRetrofit>) {
                if (response.body() != null && response.isSuccessful) {
                    listener.onSuccessResponse(response)
                } else {
                    listener.onFailureResponse(response.message())
                }
            }

            override fun onFailure(call: Call<WeatherRetrofit>, t: Throwable) {}
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