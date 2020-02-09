package com.tkdev.weatherapp.repository;

import com.tkdev.weatherapp.model.current_weather.WeatherRetrofit;
import com.tkdev.weatherapp.model.forecast_weather.ForecastRetrofit;
import com.tkdev.weatherapp.presenter.ForecastPresenter;
import com.tkdev.weatherapp.presenter.MainContract;



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

            service.getWeatherRetrofit().enqueue(new Callback<WeatherRetrofit>() {
                @Override
                public void onResponse(Call<WeatherRetrofit> call, Response<WeatherRetrofit> response) {
                    callback.onSuccess(response);
                    int i = 1+1;
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

        Call<ForecastRetrofit> call = service.getForecast();
        int i = 1+1;

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