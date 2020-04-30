package com.tkdev.weatherapp.current.data.retrofit_data_source

import com.tkdev.weatherapp.common.domain.RetrofitCalls
import com.tkdev.weatherapp.common.domain.RetrofitService
import com.tkdev.weatherapp.current.app.CurrentServiceLocator
import com.tkdev.weatherapp.current.core.CurrentContract
import com.tkdev.weatherapp.current.core.CurrentInteractor
import com.tkdev.weatherapp.current.core.CurrentWeatherDomainCity
import com.tkdev.weatherapp.current.data.CurrentRepository
import com.tkdev.weatherapp.current.data.retrofit_data_source.dto.RetrofitModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitApi {
    fun getCurrentWeather(city: String)

}

class RetrofitApiDefault() : RetrofitApi {

    private var service: RetrofitService
    private var listener: CurrentContract.APIListener

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
        listener = CurrentRepository(this)
    }

    override fun getCurrentWeather(city: String) {

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
}