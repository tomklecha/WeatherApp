package com.tkdev.weatherapp.forecast.data

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tkdev.weatherapp.common.domain.retrofit_data_source.RetrofitForecastApi
import com.tkdev.weatherapp.common.util.PreferencesVariables
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.current_prefix
import com.tkdev.weatherapp.current.core.WeatherDomain
import com.tkdev.weatherapp.current.core.WeatherErrorDomain
import com.tkdev.weatherapp.forecast.core.ForecastContract
import com.tkdev.weatherapp.forecast.core.ForecastDomain
import com.tkdev.weatherapp.forecast.core.ForecastDomainCity
import com.tkdev.weatherapp.forecast.core.ForecastErrorDomain

class ForecastReopsitory(
        private val api: RetrofitForecastApi,
        private val dto: ForecastDtoMapper,
        private val sharedPreferences: SharedPreferences
) : ForecastContract.Repository {

    override suspend fun apiRequest(city: ForecastDomainCity): ForecastDomain = try {
        dto.toDomain(api.getForecastWeather(city.value, current_prefix), current_prefix)
    } catch (e: Exception) {
        ForecastDomain.Fail(ForecastErrorDomain(e.message.toString()))
    }

    override suspend fun loadSharedPreferenceDomain(): ForecastDomain = loadFromSharedPreferences(sharedPreferences)

    override fun saveSharedPreferences(forecast: ForecastDomain.WeatherForecast) {
        modelToSharedPreferences(forecast)
    }

    private fun modelToSharedPreferences(forecast: ForecastDomain.WeatherForecast) {

        val gson = Gson()
        val json = gson.toJson(forecast)
        with(sharedPreferences.edit()) {
            putString("forecast_weather", json)
        }.apply()
    }


    private fun loadFromSharedPreferences(sharedPreferences: SharedPreferences): ForecastDomain {
        var weather : ForecastDomain = ForecastDomain.Fail(ForecastErrorDomain("No previously saved data"))
        with(sharedPreferences) {
            val json = sharedPreferences.getString("forecast_weather", null)
            if (json != null) {
                val gson = Gson()
                val type = object : TypeToken<ForecastDomain.WeatherForecast>() {}.type
                weather = gson.fromJson(json, type)
            }
        }

        return weather
    }
}


