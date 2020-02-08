package com.tkdev.weatherapp.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tkdev.weatherapp.R;
import com.tkdev.weatherapp.model.ForecastViewHolder;
import com.tkdev.weatherapp.model.Weather;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastViewHolder> {

    private List<Weather> forecasts;
    private Context context;


    public ForecastAdapter(Context context, List<Weather> forecasts){
       this.context = context;
       this.forecasts = forecasts;
   }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_forecast, null);

        return new ForecastViewHolder(view);
       }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        Weather forecast = forecasts.get(position);
        holder.setForecast(forecast);
    }

    @Override
    public int getItemCount() {
        if (forecasts == null){
            return 0;
        }
        return forecasts.size();
    }
}
