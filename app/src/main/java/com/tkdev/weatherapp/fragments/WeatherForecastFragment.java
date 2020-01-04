package com.tkdev.weatherapp.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tkdev.weatherapp.R;
import com.tkdev.weatherapp.presenter.CurrentPresenter;
import com.tkdev.weatherapp.presenter.ForecastPresenter;
import com.tkdev.weatherapp.presenter.MainContract;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherForecastFragment extends Fragment implements MainContract.View {

    private ForecastPresenter presenter;
    private WeatherForecastFragment task;
    private ProgressBar progressBar;

    private TextView currentV;
    private TextView minV;
    private TextView maxV;
    private TextView weatherV;

    public WeatherForecastFragment() {
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
        setPresenter(new ForecastPresenter((MainContract.View) getView()));

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onWeatherCreated();
//        presenter.setViewText(currentV);
//        currentV.setText("TEXT");
//

//        minV.setText(String.valueOf(weather.getTemperatureMin()));
//        maxV.setText(String.valueOf(weather.getTemperatureMax()));
//        weatherV.setText(weather.getWeather());


    }

    @Override
    public void onStart() {
        super.onStart();
//        presenter.onWeatherCreated();
//        presenter.setViewText(currentV);

    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = (ForecastPresenter) presenter;
    }


}
