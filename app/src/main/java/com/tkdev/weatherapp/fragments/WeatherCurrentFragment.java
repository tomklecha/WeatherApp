package com.tkdev.weatherapp.fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tkdev.weatherapp.R;
import com.tkdev.weatherapp.Weather;
import com.tkdev.weatherapp.WeatherCurrentTask;
import com.tkdev.weatherapp.presenter.CurrentPresenter;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherCurrentFragment extends Fragment {

    CurrentPresenter presenter;
    WeatherCurrentTask task;

    private TextView currentV;
    private TextView minV;
    private TextView maxV;
    private TextView weatherV;

    public WeatherCurrentFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather_current, container, false);

        currentV = rootView.findViewById(R.id.tempCurrent);
        minV = rootView.findViewById(R.id.tempMin);
        maxV = rootView.findViewById(R.id.tempMax);
        weatherV = rootView.findViewById(R.id.weatherDescripton);
        presenter = new CurrentPresenter(getView(),task);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Weather weather = presenter.onViewCreated();

        currentV.setText(String.valueOf(weather.getTemperatureCurrent()));
        minV.setText(String.valueOf(weather.getTemperatureMin()));
        maxV.setText(String.valueOf(weather.getTemperatureMax()));
        weatherV.setText(weather.getWeather());


    }
}
