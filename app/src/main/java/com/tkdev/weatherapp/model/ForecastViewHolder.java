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

    public ForecastViewHolder(@NonNull View itemView) {
        super(itemView);
        this.forecastTempCurrent = itemView.findViewById(R.id.forecastTempCurrent);
        this.forecastTempMin = itemView.findViewById(R.id.forecastTempMin);
        this.forecastTempMax = itemView.findViewById(R.id.forecastTempMax);
        this.forecastWeatherDescription = itemView.findViewById(R.id.forecastWeatherDescription);
    }
}
