package com.tkdev.weatherapp.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.tkdev.weatherapp.R;
import com.tkdev.weatherapp.presenter.current.CurrentPresenterImpl;
import com.tkdev.weatherapp.presenter.MainContract;

import static com.tkdev.weatherapp.repository.Utils.last_dt;


public class WeatherCurrentFragment extends Fragment implements MainContract.View {

    private CurrentPresenterImpl presenter;

    private TextView cityNameV;
    private TextView currentV;
    private TextView minV;
    private TextView maxV;
    private TextView weatherV;
    private TextView humidityV;
    private TextView lastUpdateV;
    private TextView dateV;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public WeatherCurrentFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        lastUpdateV = rootView.findViewById(R.id.city_hour_update);

        View view = getActivity().findViewById(R.id.toolbar);
        dateV = view.findViewById(R.id.toolbar_date);

        setPresenter(new CurrentPresenterImpl(this));
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        saveCurrentData();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadSharedPreferences();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = (CurrentPresenterImpl) presenter;
    }

    @Override
    public void refreshViews() {
        Log.d("PREF", "updateViews");
        presenter.onWeatherCreated();
    }

    @Override
    public void update() {

        Log.d("PREF1", presenter.setLastUpdateViewText());
        Log.d("PREF2",lastUpdateV.getText().toString());
        if (!lastUpdateV.getText().equals(presenter.setLastUpdateViewText())) {
            cityNameV.setText(presenter.setCityName());
            currentV.setText(presenter.setTemperatureCurrentTextView());
            minV.setText(presenter.setTemperatureMinimumTextView());
            maxV.setText(presenter.setTemperatureMaximumTextView());
            weatherV.setText(presenter.setWeatherDescriptionTextView());
            humidityV.setText(presenter.setHumidityViewText());
            lastUpdateV.setText(presenter.setLastUpdateViewText());
            Log.d("PREF", presenter.setLastUpdateViewText());
            dateV.setText(presenter.setDateViewText());
            Snackbar.make(getView(), "Successful update !", Snackbar.LENGTH_SHORT).show();


        }
        else {
            Snackbar.make(getView(), "Data didn't changed yet on server !", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void cancelUpdate() {
        Snackbar.make(getView(), "Try to update in : " + (10 - (System.currentTimeMillis()/1000 - last_dt)/60)  + " minutes", Snackbar.LENGTH_SHORT).show();
    }

    private void saveCurrentData() {
        if (!sharedPreferences.getString("lastUpdateV", "").equals(String.valueOf(lastUpdateV.getText()))) {
            editor = sharedPreferences.edit();
            Log.d("PREF", "Prefs are saved");
            editor.putInt("last_dt", last_dt);
            editor.putString("cityNameV", String.valueOf(cityNameV.getText()));
            editor.putString("currentV", String.valueOf(currentV.getText()));
            editor.putString("minV", String.valueOf(minV.getText()));
            editor.putString("maxV", String.valueOf(maxV.getText()));
            editor.putString("weatherV", String.valueOf(weatherV.getText()));
            editor.putString("humidityV", String.valueOf(humidityV.getText()));
            editor.putString("lastUpdateV", String.valueOf(lastUpdateV.getText()));
            editor.putString("dateV", String.valueOf(dateV.getText()));
            editor.apply();
        } else {
            Log.d("PREF", "date is same");
        }
    }

    private void loadSharedPreferences() {
        sharedPreferences = getContext().getSharedPreferences("mypref", Context.MODE_PRIVATE);
        last_dt = sharedPreferences.getInt("last_dt", 0);
        cityNameV.setText(sharedPreferences.getString("cityNameV", "Update views to see content"));
        currentV.setText(sharedPreferences.getString("currentV", ""));
        minV.setText(sharedPreferences.getString("minV", ""));
        maxV.setText(sharedPreferences.getString("maxV", ""));
        weatherV.setText(sharedPreferences.getString("weatherV", ""));
        humidityV.setText(sharedPreferences.getString("humidityV", ""));
        lastUpdateV.setText(sharedPreferences.getString("lastUpdateV", ""));
        dateV.setText(sharedPreferences.getString("dateV", ""));
    }


}



