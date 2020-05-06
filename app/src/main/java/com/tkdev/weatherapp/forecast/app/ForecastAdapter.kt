package com.tkdev.weatherapp.forecast.app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tkdev.weatherapp.R
import com.tkdev.weatherapp.common.domain.retrofit_data_source.forecast_dto.ForecastRetrofit

class ForecastAdapter(private val forecasts: ForecastRetrofit, private val prefix: String) : RecyclerView.Adapter<ForecastViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ForecastViewHolder(inflater.inflate(R.layout.list_forecast, null))
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecastList = forecasts.list[position]
        val forecastTimezone = forecasts.city.timezone
        holder.setForecast(forecastList, forecastTimezone, prefix)
    }

    override fun getItemCount(): Int {
        return forecasts.list.size
    }

}