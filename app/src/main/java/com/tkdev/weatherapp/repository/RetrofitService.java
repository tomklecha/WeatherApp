package com.tkdev.weatherapp.repository;

import com.tkdev.weatherapp.model.current_weather.WeatherRetrofit;
import com.tkdev.weatherapp.model.forecast_weather.ForecastRetrofit;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.tkdev.weatherapp.repository.Utils.WEATHER_API_KEY;
import static com.tkdev.weatherapp.repository.Utils.WEATHER_API_PREFIX;
import static com.tkdev.weatherapp.repository.Utils.WEATHER_CITY_ID;
import static com.tkdev.weatherapp.repository.Utils.WEATHER_CURRENT_REQUEST;
import static com.tkdev.weatherapp.repository.Utils.WEATHER_FORECAST_REQUEST;
import static com.tkdev.weatherapp.repository.Utils.WEATHER_TEMPERATURE;
import static com.tkdev.weatherapp.repository.Utils.WEATHER_TEMPERATURE_PREFIX;

public interface RetrofitService {

    @GET(WEATHER_CURRENT_REQUEST + WEATHER_CITY_ID + WEATHER_API_PREFIX + WEATHER_API_KEY + WEATHER_TEMPERATURE_PREFIX + WEATHER_TEMPERATURE)
    Call<WeatherRetrofit> getCurrentWeather();

    @GET(WEATHER_FORECAST_REQUEST + WEATHER_CITY_ID + WEATHER_API_PREFIX + WEATHER_API_KEY + WEATHER_TEMPERATURE_PREFIX + WEATHER_TEMPERATURE)
    Call<ForecastRetrofit> getForecastWeather();
}
