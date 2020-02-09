package com.tkdev.weatherapp.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tkdev.weatherapp.R;
import com.tkdev.weatherapp.presenter.CurrentPresenter;
import com.tkdev.weatherapp.presenter.MainContract;


public class WeatherCurrentFragment extends Fragment implements MainContract.View {

    private CurrentPresenter presenter;

    private TextView cityNameV;
    private TextView currentV;
    private TextView minV;
    private TextView maxV;
    private TextView weatherV;
    private TextView humidityV;
    private TextView lastUpdate;
    private TextView dateV;

    public WeatherCurrentFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setPresenter(new CurrentPresenter((MainContract.View) getView()));
        presenter.onWeatherCreated();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather_current, container, false);

        cityNameV = rootView.findViewById(R.id.city_name);
        currentV = rootView.findViewById(R.id.city_temp_current);
        minV = rootView.findViewById(R.id.city_temp_min);
        maxV = rootView.findViewById(R.id.city_temp_max);
        weatherV = rootView.findViewById(R.id.city_weather);
        humidityV = rootView.findViewById(R.id.city_humidity);
        lastUpdate = rootView.findViewById(R.id.city_hour_update);

        View view = getActivity().findViewById(R.id.toolbar);
        dateV = view.findViewById(R.id.toolbar_date);

        return rootView;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("KEY", currentV.getText().toString());
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }



    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = (CurrentPresenter) presenter;
    }


    @Override
    public void refreshViews() {
        cityNameV.setText(presenter.setCityName());
        currentV.setText(presenter.setTemperatureCurrentTextView());
        minV.setText(presenter.setTemperatureMinimumTextView());
        maxV.setText(presenter.setTemperatureMaximumTextView());
        weatherV.setText(presenter.setWeatherDescriptionTextView());
        humidityV.setText(presenter.setHumidityViewText());
        lastUpdate.setText(presenter.setLastUpdateViewText());
        dateV.setText(presenter.setDateViewText());
    }

    @Override
    public void setText(String setText) {

    }
}



