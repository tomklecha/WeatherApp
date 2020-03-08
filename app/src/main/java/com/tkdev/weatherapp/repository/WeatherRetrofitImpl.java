package com.tkdev.weatherapp.repository;

import android.util.Log;

import com.tkdev.weatherapp.model.current_weather.WeatherRetrofit;
import com.tkdev.weatherapp.model.forecast_weather.ForecastRetrofit;
import com.tkdev.weatherapp.presenter.MainContract;


import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.tkdev.weatherapp.repository.Utils.WEATHER_REQUEST_BASE;

public class WeatherRetrofitImpl implements MainContract.Model {

    RetrofitService service;


    @Override
    public void getWeather(MainContract.APIListener callback) {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        RetrofitService service = new Retrofit.Builder()
                .baseUrl(WEATHER_REQUEST_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(RetrofitService.class);

        service.getCurrentWeather().enqueue(new Callback<WeatherRetrofit>() {
            @Override
            public void onResponse(Call<WeatherRetrofit> call, Response<WeatherRetrofit> response) {

                if (response.body() != null && response.isSuccessful()) {
                 Log.d("Tag", "response code" + response.code());


                    callback.onSuccess(response);
                    Log.d("Tag", "response body" + response.body());

                }
            }

            @Override
            public void onFailure(Call<WeatherRetrofit> call, Throwable t) {

            }

        });
    }

    @Override
    public void getForecast(MainContract.APIForecastListener callback) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WEATHER_REQUEST_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        service = retrofit.create(RetrofitService.class);

        Call<ForecastRetrofit> call = service.getForecastWeather();

        call.enqueue(new Callback<ForecastRetrofit>() {
            @Override
            public void onResponse(Call<ForecastRetrofit> call, Response<ForecastRetrofit> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ForecastRetrofit> call, Throwable t) {

            }
        });


    }


}