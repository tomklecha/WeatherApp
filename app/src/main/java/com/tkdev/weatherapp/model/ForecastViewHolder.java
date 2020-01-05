package com.tkdev.weatherapp.model;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tkdev.weatherapp.R;

public class ForecastViewHolder extends RecyclerView.ViewHolder {

    public TextView forecastTempCurrent;
    public TextView forecastTempMin;
    public TextView forecastTempMax;
    public TextView forecastWeatherDescription;
    public TextView forecastDayOfForecast;
    public TextView forecastHourOfForecast;

    public ForecastViewHolder(@NonNull View itemView) {
        super(itemView);
        this.forecastTempCurrent = itemView.findViewById(R.id.forecast_temp_current);
        this.forecastTempMin = itemView.findViewById(R.id.forecast_temp_min);
        this.forecastTempMax = itemView.findViewById(R.id.forecast_temp_max);
        this.forecastWeatherDescription = itemView.findViewById(R.id.forecast_weather);
        this.forecastDayOfForecast = itemView.findViewById(R.id.forecast_day);
        this.forecastHourOfForecast = itemView.findViewById(R.id.forecast_hour);
    }
}
