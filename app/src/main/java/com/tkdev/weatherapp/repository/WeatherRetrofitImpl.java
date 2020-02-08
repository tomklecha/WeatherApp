package com.tkdev.weatherapp.repository;

import com.tkdev.weatherapp.model.Weather;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.tkdev.weatherapp.tasks.Utils.WEATHER_REQUEST_BASE;

public class WeatherRetrofitImpl {

    WeatherRetrofit service;
    Weather weather;
    List<Weather> forecasts;

    public Weather getWeather() {

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

        service = retrofit.create(WeatherRetrofit.class);

        Call<Weather> call = service.getWeather();


        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                weather = response.body();

                }


            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });

        return weather;
    }
    public List<Weather> getForecasts() {

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

        service = retrofit.create(WeatherRetrofit.class);

        Call<List<Weather>> call = service.getForecast();

        call.enqueue(new Callback<List<Weather>>() {
            @Override
            public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {

            }

            @Override
            public void onFailure(Call<List<Weather>> call, Throwable t) {

            }
        });

        return forecasts;
    }

}