package com.tkdev.weatherapp.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tkdev.weatherapp.R;
import com.tkdev.weatherapp.model.current_weather.Weather;
import com.tkdev.weatherapp.model.forecast_weather.ForecastRetrofit;
import com.tkdev.weatherapp.model.forecast_weather.ForecastViewHolder;
import com.tkdev.weatherapp.model.current_weather.WeatherRetrofit;
import com.tkdev.weatherapp.model.forecast_weather.List;

import java.util.ArrayList;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastViewHolder> {

    private ForecastRetrofit forecasts;
    private Context context;

    public ForecastAdapter(ForecastRetrofit forecasts, Context context) {
        this.forecasts = forecasts;
        this.context = context;
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_forecast, null);

        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        List forecastList = forecasts.getList().get(position);
        holder.setForecast(forecastList);
    }

    @Override
    public int getItemCount() {
        if (forecasts == null) {
            return 0;
        }
        return forecasts.getList().size();
    }
}
