package com.tkdev.weatherapp.common.domain

import android.view.View
import androidx.annotation.VisibleForTesting
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import kotlin.math.round

class RetrofitCalls {
    companion object {
        const val AND_SYMBOL = "&"
        const val WEATHER_REQUEST_BASE = "https://api.openweathermap.org/data/2.5/"
        const val WEATHER_CURRENT_REQUEST = "weather?"
        const val WEATHER_FORECAST_REQUEST = "forecast?"
        const val WEATHER_CITY_ID_PREFIX = "id"
        const val WEATHER_CITY_NAME = "q"
        const val WEATHER_CITY_ID = "2643743"
        const val WEATHER_CITY_ID_RETROFIT = "{city}"
        const val WEATHER_API_PREFIX = "APPID="
        const val WEATHER_API_KEY = "5b08b54ce198509d241991110864cab4"
        const val WEATHER_TEMPERATURE_PREFIX = "units"
        const val WEATHER_TEMPERATURE_CELSIUS = "metric"
        const val WEATHER_TEMPERATURE_FARHENHEIT = "imperial"

        const val CELSIUS_SYMBOL = "°"
        const val KELVIN_SYMBOL = "°K"
        const val FARHENHEIT_SYMBOL = "°F"
        const val HUMIDITY_SYMBOL = " %"
        const val FORECAST_DAY_PATTERN = "EEE"
        const val FORECAST_HOUR_PATTERN = "HH:mm"
        const val LAST_UPDATE_PATTERN = "HH:mm"
        const val DATE_PATTERN = "EEE dd-MM-yyyy"


        fun temperaturePrefix(temp: Double, prefix: String): String{
            var newTempPref: String = (round(temp * 10) / 10).toString()
            newTempPref += when(prefix){
                WEATHER_TEMPERATURE_CELSIUS -> CELSIUS_SYMBOL
                WEATHER_TEMPERATURE_FARHENHEIT -> FARHENHEIT_SYMBOL
                else -> KELVIN_SYMBOL
            }
            return newTempPref
        }

        fun humidityPrefix(humidity: Int): String = humidity.toString() + HUMIDITY_SYMBOL

        fun datePattern(dt: Long, pattern: String): String
            = SimpleDateFormat(pattern).format(dt)
    }
}