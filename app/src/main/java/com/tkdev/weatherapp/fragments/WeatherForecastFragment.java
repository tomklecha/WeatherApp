package com.tkdev.weatherapp.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tkdev.weatherapp.R;
import com.tkdev.weatherapp.adapters.ForecastAdapter;
import com.tkdev.weatherapp.presenter.ForecastPresenter;
import com.tkdev.weatherapp.presenter.MainContract;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherForecastFragment extends Fragment implements MainContract.View {

    private ForecastPresenter presenter;
    private RecyclerView forecastRecycleView;
    private ForecastAdapter adapter;

    public WeatherForecastFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(new ForecastPresenter((MainContract.View) getView()));
        presenter.onWeatherCreated();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather_forecast, container, false);

        forecastRecycleView = rootView.findViewById(R.id.forecast_recycler_view);
        forecastRecycleView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        adapter = new ForecastAdapter(getContext(), presenter.getForecasts());
        forecastRecycleView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = (ForecastPresenter) presenter;
    }

}