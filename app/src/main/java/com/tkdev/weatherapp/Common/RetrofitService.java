package com.tkdev.weatherapp.Common;

import com.tkdev.weatherapp.Current.model.WeatherRetrofit;
import com.tkdev.weatherapp.Forecast.model.ForecastRetrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.tkdev.weatherapp.Common.RetrofitCalls.AND_SYMBOL;
import static com.tkdev.weatherapp.Common.RetrofitCalls.WEATHER_API_KEY;
import static com.tkdev.weatherapp.Common.RetrofitCalls.WEATHER_API_PREFIX;
import static com.tkdev.weatherapp.Common.RetrofitCalls.WEATHER_CITY_NAME;
import static com.tkdev.weatherapp.Common.RetrofitCalls.WEATHER_CURRENT_REQUEST;
import static com.tkdev.weatherapp.Common.RetrofitCalls.WEATHER_FORECAST_REQUEST;
import static com.tkdev.weatherapp.Common.RetrofitCalls.WEATHER_TEMPERATURE_CELSIUS;
import static com.tkdev.weatherapp.Common.RetrofitCalls.WEATHER_TEMPERATURE_PREFIX;

public interface RetrofitService {


    @GET(
            WEATHER_CURRENT_REQUEST + WEATHER_API_PREFIX + WEATHER_API_KEY +
                    AND_SYMBOL + WEATHER_TEMPERATURE_PREFIX + WEATHER_TEMPERATURE_CELSIUS
    )
    Call<WeatherRetrofit> getCurrentWeather(@Query(WEATHER_CITY_NAME) String city);

    @GET(
            WEATHER_FORECAST_REQUEST + WEATHER_API_PREFIX + WEATHER_API_KEY +
                    AND_SYMBOL + WEATHER_TEMPERATURE_PREFIX + WEATHER_TEMPERATURE_CELSIUS
    )
    Call<ForecastRetrofit> getForecastWeather(@Query(WEATHER_CITY_NAME) String city);

}
