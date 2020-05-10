package com.tkdev.weatherapp.forecast;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tkdev.weatherapp.R;
import com.tkdev.weatherapp.common.MainContract;

import java.util.ArrayList;

public class WeatherForecastFragment extends Fragment implements MainContract.View {

    private static final String TAG = "WeatherForecastFragment";

    private ForecastPresenterImpl presenter;
    private RecyclerView forecastRecycleView;
    private ForecastAdapter adapter;

    public WeatherForecastFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather_forecast, container, false);

        forecastRecycleView = rootView.findViewById(R.id.forecast_recycler_view);
        forecastRecycleView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        setPresenter(new ForecastPresenterImpl(this));

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (presenter.getForecasts() != null && adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = (ForecastPresenterImpl) presenter;
    }

    @Override
    public void showWeatherByCity(String city) {
            Log.d(TAG, "Show current forecast for: " + city);
            presenter.onRequestWeather(city);
    }

    @Override
    public void update() {
            adapter = new ForecastAdapter(presenter.getForecasts(), getContext());
            forecastRecycleView.setAdapter(adapter);
    }

    @Override
    public void cancelUpdate() {

    }

    @Override
    public void onFailUpdate(String message) {

    }

    @Override
    public String shareWeather(ArrayList<Boolean> booleanList) {
        return null;
    }
}

