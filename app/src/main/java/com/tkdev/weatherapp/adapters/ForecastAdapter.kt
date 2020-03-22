package com.tkdev.weatherapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tkdev.weatherapp.R
import com.tkdev.weatherapp.model.forecast_weather.ForecastRetrofit
import com.tkdev.weatherapp.model.forecast_weather.ForecastViewHolder

class ForecastAdapter(private val forecasts: ForecastRetrofit) : RecyclerView.Adapter<ForecastViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ForecastViewHolder(inflater.inflate(R.layout.list_forecast, null))
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecastList = forecasts.list[position]
        val forecastTimezone = forecastList.dt + forecasts.city.timezone
        holder.setForecast(forecastList, forecastTimezone)
    }

    override fun getItemCount(): Int {
        return forecasts.list.size
    }

}