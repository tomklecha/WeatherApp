package com.tkdev.weatherapp.repository;

import com.tkdev.weatherapp.model.Weather;
import com.tkdev.weatherapp.modelretro.WeatherRetrofit;
import com.tkdev.weatherapp.presenter.CurrentPresenter;
import com.tkdev.weatherapp.presenter.MainContract;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.tkdev.weatherapp.tasks.Utils.WEATHER_REQUEST_BASE;

public class WeatherRetrofitImpl implements MainContract.Model {

    RetrofitService service;
    List<Weather> forecasts;


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

            service.getWeatherRetrofit().enqueue(new Callback<WeatherRetrofit>() {
                @Override
                public void onResponse(Call<WeatherRetrofit> call, Response<WeatherRetrofit> response) {
                    callback.onSuccess(response);
                }

                @Override
                public void onFailure(Call<WeatherRetrofit> call, Throwable t) {

                }
            });
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

        service = retrofit.create(RetrofitService.class);

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

    public interface SuccessCallback<T> {

        /**
         * Callback method for successful service calls.
         *
         * @param result The result received from the service.
         */
        void onSuccess(T result);

    }

}