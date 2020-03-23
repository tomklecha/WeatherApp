package com.tkdev.weatherapp.Common;

import com.tkdev.weatherapp.Current.model.WeatherRetrofit;
import com.tkdev.weatherapp.Forecast.model.ForecastRetrofit;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.tkdev.weatherapp.Common.RetrofitCalls.WEATHER_REQUEST_BASE;

public class WeatherRetrofitImpl implements MainContract.Model {

    RetrofitService service;


    @Override
    public void getWeather(MainContract.APIListener callback, String city) {

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

        Call<WeatherRetrofit> call = service.getCurrentWeather(city);

        call.enqueue(new Callback<WeatherRetrofit>() {
            @Override
            public void onResponse(Call<WeatherRetrofit> call, Response<WeatherRetrofit> response) {

                if (response.body() != null && response.isSuccessful()) {


                    callback.onSuccessResponse(response);

                } else {
                    callback.onFailureResponse(response.message());
                }
            }

            @Override
            public void onFailure(Call<WeatherRetrofit> call, Throwable t) {
            }

        });
    }


    @Override
    public void getWeather(MainContract.APIForecastListener callback, String city) {
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

        Call<ForecastRetrofit> call = service.getForecastWeather(city);

        call.enqueue(new Callback<ForecastRetrofit>() {
            @Override
            public void onResponse(Call<ForecastRetrofit> call, Response<ForecastRetrofit> response) {
                if (response.body() != null && response.isSuccessful()) {

                    callback.onSuccessResponse(response);

                } else {
                    callback.onFailureResponse(response.message());
                }

            }

            @Override
            public void onFailure(Call<ForecastRetrofit> call, Throwable t) {

            }
        });


    }


}