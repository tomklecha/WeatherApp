package com.tkdev.weatherapp.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tkdev.weatherapp.R;
import com.tkdev.weatherapp.model.Weather;
import com.tkdev.weatherapp.presenter.CurrentPresenter;
import com.tkdev.weatherapp.presenter.MainContract;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherCurrentFragment extends Fragment implements MainContract.View {

    private CurrentPresenter presenter;
    private WeatherCurrentFragment task;;
    private ProgressBar progressBar;
    private FloatingActionButton fab;

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
        progressBar = rootView.findViewById(R.id.progressBar);
        fab = rootView.findViewById(R.id.fab);
        setPresenter(new CurrentPresenter((MainContract.View) getView()));

        return rootView;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState == null){
            presenter.onWeatherCreated();
            presenter.setCurrentViewText(currentV);
        }


        fab.setOnClickListener(v -> WeatherCurrentFragment.this.getFragmentManager().beginTransaction()
                .replace(R.id.content_main, new WeatherForecastFragment(), "TAG")
                .addToBackStack("TAG")
                .commit());
//        currentV.setText(String.valueOf(presenter.onWeatherCreated().getTemperatureCurrent()));
//        minV.setText(String.valueOf(weather.getTemperatureMin()));
//        maxV.setText(String.valueOf(weather.getTemperatureMax()));
//        weatherV.setText(weather.getWeather());


    }


    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = (CurrentPresenter) presenter;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAGTAG", "view destroyed");
    }


}
