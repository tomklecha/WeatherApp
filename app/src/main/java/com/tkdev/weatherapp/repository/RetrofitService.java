package com.tkdev.weatherapp.repository;

import com.tkdev.weatherapp.model.Weather;
import com.tkdev.weatherapp.modelretro.WeatherRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.tkdev.weatherapp.tasks.Utils.WEATHER_API_KEY;
import static com.tkdev.weatherapp.tasks.Utils.WEATHER_API_PREFIX;
import static com.tkdev.weatherapp.tasks.Utils.WEATHER_CITY_ID;
import static com.tkdev.weatherapp.tasks.Utils.WEATHER_CURRENT_REQUEST;
import static com.tkdev.weatherapp.tasks.Utils.WEATHER_FORECAST_REQUEST;
import static com.tkdev.weatherapp.tasks.Utils.WEATHER_TEMPERATURE;
import static com.tkdev.weatherapp.tasks.Utils.WEATHER_TEMPERATURE_PREFIX;

public interface RetrofitService {

    @GET(WEATHER_CURRENT_REQUEST + WEATHER_CITY_ID + WEATHER_API_PREFIX + WEATHER_API_KEY + WEATHER_TEMPERATURE_PREFIX + WEATHER_TEMPERATURE)
    Call<WeatherRetrofit> getWeatherRetrofit();

    @GET(WEATHER_FORECAST_REQUEST + WEATHER_CITY_ID + WEATHER_API_PREFIX + WEATHER_API_KEY)
    Call<List<Weather>> getForecast();
}
