package com.tkdev.weatherapp.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tkdev.weatherapp.R
import com.tkdev.weatherapp.forecast.model.ForecastRetrofit
import com.tkdev.weatherapp.forecast.model.ForecastViewHolder

class ForecastAdapter(private val forecasts: ForecastRetrofit) : RecyclerView.Adapter<ForecastViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ForecastViewHolder(inflater.inflate(R.layout.list_forecast, null))
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecastList = forecasts.list[position]
        val forecastTimezone = (forecastList.dt + forecasts.city.timezone - 3600).toLong() * 1000
        holder.setForecast(forecastList, forecastTimezone)
    }

    override fun getItemCount(): Int {
        return forecasts.list.size
    }

}