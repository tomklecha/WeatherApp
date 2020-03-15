package com.tkdev.weatherapp.repository;

import com.tkdev.weatherapp.model.current_weather.WeatherRetrofit;
import com.tkdev.weatherapp.model.forecast_weather.ForecastRetrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.tkdev.weatherapp.repository.Utils.AND_SYMBOL;
import static com.tkdev.weatherapp.repository.Utils.WEATHER_API_KEY;
import static com.tkdev.weatherapp.repository.Utils.WEATHER_API_PREFIX;
import static com.tkdev.weatherapp.repository.Utils.WEATHER_CITY_ID;
import static com.tkdev.weatherapp.repository.Utils.WEATHER_CITY_ID_PREFIX;
import static com.tkdev.weatherapp.repository.Utils.WEATHER_CITY_ID_RETROFIT;
import static com.tkdev.weatherapp.repository.Utils.WEATHER_CITY_NAME;
import static com.tkdev.weatherapp.repository.Utils.WEATHER_CURRENT_REQUEST;
import static com.tkdev.weatherapp.repository.Utils.WEATHER_FORECAST_REQUEST;
import static com.tkdev.weatherapp.repository.Utils.WEATHER_TEMPERATURE;
import static com.tkdev.weatherapp.repository.Utils.WEATHER_TEMPERATURE_PREFIX;

public interface RetrofitService {


    @GET(
            WEATHER_CURRENT_REQUEST + WEATHER_API_PREFIX + WEATHER_API_KEY +
                    AND_SYMBOL + WEATHER_TEMPERATURE_PREFIX + WEATHER_TEMPERATURE
    )
    Call<WeatherRetrofit> getCurrentWeather(@Query(WEATHER_CITY_NAME) String city);

    @GET(
            WEATHER_FORECAST_REQUEST + WEATHER_API_PREFIX + WEATHER_API_KEY +
                    AND_SYMBOL + WEATHER_TEMPERATURE_PREFIX + WEATHER_TEMPERATURE
    )
    Call<ForecastRetrofit> getForecastWeather(@Query(WEATHER_CITY_NAME) String city);

}
