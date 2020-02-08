package com.tkdev.weatherapp.model;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tkdev.weatherapp.R;

import java.text.SimpleDateFormat;

public class ForecastViewHolder extends RecyclerView.ViewHolder {

    public TextView forecastTempCurrent;
    public TextView forecastTempMin;
    public TextView forecastTempMax;
    public TextView forecastWeatherDescription;
    public TextView forecastDayOfForecast;
    public TextView forecastHourOfForecast;

    private static final String FORECAST_DAY_PATTERN = "EEE";
    private static final String FORECAST_HOUR_PATTERN = "HH:mm";

    public ForecastViewHolder(@NonNull View itemView) {
        super(itemView);
        this.forecastTempCurrent = itemView.findViewById(R.id.forecast_temp_current);
        this.forecastTempMin = itemView.findViewById(R.id.forecast_temp_min);
        this.forecastTempMax = itemView.findViewById(R.id.forecast_temp_max);
        this.forecastWeatherDescription = itemView.findViewById(R.id.forecast_weather);
        this.forecastDayOfForecast = itemView.findViewById(R.id.forecast_day);
        this.forecastHourOfForecast = itemView.findViewById(R.id.forecast_hour);
    }


    public ForecastViewHolder setForecast(Weather forecast) {

        SimpleDateFormat weatherDay = new SimpleDateFormat(FORECAST_DAY_PATTERN);
        SimpleDateFormat weatherHour = new SimpleDateFormat(FORECAST_HOUR_PATTERN);

        String tempCurrent = forecast.getTemperatureCurrent() + Weather.TEMPERATURE_SUFFIX;
        String tempMin = forecast.getTemperatureMin() + Weather.TEMPERATURE_SUFFIX;
        String tempMax = forecast.getTemperatureMax() + Weather.TEMPERATURE_SUFFIX;

        this.forecastTempCurrent.setText(tempCurrent);
        this.forecastTempMin.setText(tempMin);
        this.forecastTempMax.setText(tempMax);
        this.forecastWeatherDescription.setText(forecast.getWeatherDescription());
        this.forecastDayOfForecast.setText(weatherDay.format(forecast.getDate()));
        this.forecastHourOfForecast.setText(weatherHour.format(forecast.getDate()));

        return this;
    }

}
