package com.tkdev.weatherapp.current.data

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tkdev.weatherapp.common.domain.retrofit_data_source.RetrofitCurrentApi
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.current_city
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.current_prefix
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.last_dt
import com.tkdev.weatherapp.current.core.*

class CurrentRepository(
        private val api: RetrofitCurrentApi,
        private val dto: CurrentDtoMapper,
        private val sharedPreferences: SharedPreferences)
    : CurrentContract.Repository {

    private lateinit var weather: WeatherDomain

    override suspend fun apiRequest(city: WeatherDomainCity): WeatherDomain = try {
        dto.toDomain(api.getCurrentWeather(city.value, current_prefix), current_prefix)

    } catch (e: Exception) {
        WeatherDomain.Fail(WeatherErrorDomain(e.message.toString()))
    }

    override suspend fun loadSharedPreferenceDomain(): WeatherDomain = loadFromSharedPreferences(sharedPreferences)

    override suspend fun saveSharedPreferences(weather: WeatherDomain.Weather) {
        domainToSharedPreferences(weather)
    }

    private fun domainToSharedPreferences(weather: WeatherDomain.Weather) {
        last_dt = weather.lastUpdate.value
        current_city = weather.city.value
        current_prefix = weather.tempObject.prefix.value

        val gson = Gson()
        val json = gson.toJson(weather)
        with(sharedPreferences.edit()) {
            putString("current_weather", json)
        }.apply()
    }


    private fun loadFromSharedPreferences(sharedPreferences: SharedPreferences): WeatherDomain {
        var isSuccess = false

        with(sharedPreferences) {
            val json = sharedPreferences.getString("current_weather", null)
            if (json != null) {
                val gson = Gson()
                val type = object : TypeToken<WeatherDomain.Weather>() {}.type
                weather = gson.fromJson(json, type)
                isSuccess = true
            }
        }

        return when(isSuccess){
            true -> weather
            false -> WeatherDomain.Fail(WeatherErrorDomain("No previously saved data"))
        }
    }
}






