package com.tkdev.weatherapp.model.forecast_weather

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tkdev.weatherapp.utils.RetrofitCalls.*
import kotlinx.android.synthetic.main.list_forecast.view.*

class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var forecastTempCurrent: TextView = itemView.forecast_temp_current
    private var forecastTempMin: TextView = itemView.forecast_temp_min
    private var forecastTempMax: TextView = itemView.forecast_temp_max
    private var forecastWeatherDescription: TextView = itemView.forecast_weather
    private var forecastDayOfForecast: TextView = itemView.forecast_day
    private var forecastHourOfForecast: TextView = itemView.forecast_hour

    fun setForecast(forecast: List, forecastTimezone: Int): ForecastViewHolder
    {

        this.forecastTempCurrent.text = temperaturePrefix(forecast.main.temp);
        this.forecastTempMin.text = temperaturePrefix(forecast.main.tempMin);
        this.forecastTempMax.text = temperaturePrefix(forecast.main.tempMax);
        this.forecastWeatherDescription.text = forecast.weather[0].main;
        this.forecastDayOfForecast.text = datePattern(forecastTimezone, FORECAST_DAY_PATTERN);
        this.forecastHourOfForecast.text = datePattern(forecastTimezone, FORECAST_HOUR_PATTERN);

        return this
    }
}
