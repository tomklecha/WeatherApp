package com.tkdev.weatherapp.current.data

import android.content.SharedPreferences
import com.tkdev.weatherapp.common.domain.retrofit_data_source.RetrofitCurrentApi
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.CITY_NAME_VIEW
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.CURRENT_PREFIX
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.CURRENT_TEMP_VIEW
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.HUMIDITY_VIEW
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.LAST_UPDATE_DATE_VIEW
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.MAX_TEMP_VIEW
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.MIN_TEMP_VIEW
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.TIMEZONE_VALUE
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.WEATHER_DESCRIPTION_VIEW
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.current_city
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.current_prefix
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.last_dt
import com.tkdev.weatherapp.current.core.*

class CurrentRepository(
        private val api: RetrofitCurrentApi,
        private val dto: CurrentDtoMapper,
        private val sharedPreferences: SharedPreferences)
    : CurrentContract.Repository {

    override suspend fun apiRequest(city: WeatherDomainCity): WeatherDomain = try {
        dto.toDomain(api.getCurrentWeather(city.value, current_prefix), current_prefix)

    } catch (e: Exception) {
        WeatherDomain.Fail(WeatherErrorDomain(e.message.toString()))
    }

    override fun loadSharedPreferenceDomain(): WeatherDomain.Weather = loadFromSharedPreferences(sharedPreferences)

    override fun saveSharedPreferences(weather: WeatherDomain.Weather) {
        modelToSharedPreferences(weather)
    }

    private fun modelToSharedPreferences(weather: WeatherDomain.Weather) {
        last_dt = weather.lastUpdate.value
        current_city = weather.city.value
        current_prefix = weather.tempObject.prefix.value
        with(sharedPreferences.edit()) {
            putInt(TIMEZONE_VALUE, weather.timezone.value)
            putString(CITY_NAME_VIEW, weather.city.value)
            putString(CURRENT_TEMP_VIEW, weather.tempObject.temp.value.toString())
            putString(MIN_TEMP_VIEW, weather.tempObject.tempMin.value.toString())
            putString(MAX_TEMP_VIEW, weather.tempObject.tempMax.value.toString())
            putString(CURRENT_PREFIX, weather.tempObject.prefix.value)
            putInt(HUMIDITY_VIEW, weather.humidity.value)
            putString(WEATHER_DESCRIPTION_VIEW, weather.description.value)
            putInt(LAST_UPDATE_DATE_VIEW, weather.lastUpdate.value)
        }.apply()
    }


    private fun loadFromSharedPreferences(sharedPreferences: SharedPreferences): WeatherDomain.Weather =
            with(sharedPreferences) {
                WeatherDomain.Weather(WeatherDomainCity(sharedPreferences.getString(CITY_NAME_VIEW, "--")),
                        WeatherDomainTempObject(
                                WeatherDomainTemp((sharedPreferences.getString(CURRENT_TEMP_VIEW, "0.0").toDouble())),
                                WeatherDomainTempMin((sharedPreferences.getString(MIN_TEMP_VIEW, "0.0").toDouble())),
                                WeatherDomainTempMax((sharedPreferences.getString(MAX_TEMP_VIEW, "0.0").toDouble())),
                                WeatherDomainTempPrefix(sharedPreferences.getString(CURRENT_PREFIX, "--"))),
                        WeatherDomainHumidity(sharedPreferences.getInt(HUMIDITY_VIEW, 0)),
                        WeatherDomainDescription(sharedPreferences.getString(WEATHER_DESCRIPTION_VIEW, "--")),
                        WeatherDomainLastUpdate(sharedPreferences.getInt(LAST_UPDATE_DATE_VIEW, 0)),
                        WeatherDomainTimezone(sharedPreferences.getInt(TIMEZONE_VALUE, 0))
                )
            }


}






