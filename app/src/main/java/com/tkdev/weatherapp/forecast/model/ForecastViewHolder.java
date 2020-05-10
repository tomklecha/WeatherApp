package com.tkdev.weatherapp.forecast.model;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tkdev.weatherapp.R;
import com.tkdev.weatherapp.common.RetrofitCalls;

import static com.tkdev.weatherapp.common.RetrofitCalls.FORECAST_DAY_PATTERN;
import static com.tkdev.weatherapp.common.RetrofitCalls.FORECAST_HOUR_PATTERN;
import static com.tkdev.weatherapp.common.RetrofitCalls.datePattern;

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


    public ForecastViewHolder setForecast(List forecast, int forecastTimezone) {

        this.forecastTempCurrent.setText(RetrofitCalls.temperaturePrefix(forecast.getMain().getTemp()));
        this.forecastTempMin.setText(RetrofitCalls.temperaturePrefix(forecast.getMain().getTempMin()));
        this.forecastTempMax.setText(RetrofitCalls.temperaturePrefix(forecast.getMain().getTempMax()));
        this.forecastWeatherDescription.setText(forecast.getWeather().get(0).getMain());
        this.forecastDayOfForecast.setText(datePattern(forecastTimezone, FORECAST_DAY_PATTERN));
        this.forecastHourOfForecast.setText(datePattern(forecastTimezone, FORECAST_HOUR_PATTERN));

        return this;
    }

}
