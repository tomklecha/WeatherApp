package com.tkdev.weatherapp.forecast.app

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tkdev.weatherapp.common.domain.RetrofitCalls.Companion.FORECAST_DAY_PATTERN
import com.tkdev.weatherapp.common.domain.RetrofitCalls.Companion.FORECAST_HOUR_PATTERN
import com.tkdev.weatherapp.common.domain.RetrofitCalls.Companion.datePattern
import com.tkdev.weatherapp.common.domain.RetrofitCalls.Companion.temperaturePrefix
import com.tkdev.weatherapp.common.domain.retrofit_data_source.forecast_dto.List
import kotlinx.android.synthetic.main.list_forecast.view.*

class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var forecastTempCurrent: TextView = itemView.forecast_temp_current
    private var forecastTempMin: TextView = itemView.forecast_temp_min
    private var forecastTempMax: TextView = itemView.forecast_temp_max
    private var forecastWeatherDescription: TextView = itemView.forecast_weather
    private var forecastDayOfForecast: TextView = itemView.forecast_day
    private var forecastHourOfForecast: TextView = itemView.forecast_hour

    fun setForecast(forecast: List, forecastTimezone: Int, prefix: String) {
        forecastTempCurrent.text = temperaturePrefix(forecast.main.temp,prefix)
        forecastTempMin.text = temperaturePrefix(forecast.main.tempMin,prefix)
        forecastTempMax.text = temperaturePrefix(forecast.main.tempMax,prefix)
        forecastWeatherDescription.text = forecast.weather[0].main
        forecastDayOfForecast.text = datePattern(forecast.dt, forecastTimezone, FORECAST_DAY_PATTERN)
        forecastHourOfForecast.text = datePattern(forecast.dt, forecastTimezone, FORECAST_HOUR_PATTERN)
    }
}
