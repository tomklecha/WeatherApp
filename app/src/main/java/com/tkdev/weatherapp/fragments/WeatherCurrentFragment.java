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
import com.tkdev.weatherapp.R;
import com.tkdev.weatherapp.presenter.current.CurrentPresenterImpl;
import com.tkdev.weatherapp.presenter.MainContract;
import java.util.Objects;
import static com.tkdev.weatherapp.repository.Utils.*;

public class WeatherCurrentFragment extends Fragment implements MainContract.View {

    private static final String TAG = "WeatherCurrentFragment";
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

        View view = Objects.requireNonNull(getActivity()).findViewById(R.id.toolbar);
        dateV = view.findViewById(R.id.toolbar_date);

        setPresenter(new CurrentPresenterImpl(this));
        loadSharedPreferences();
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = (CurrentPresenterImpl) presenter;
    }

    @Override
    public void showWeatherByCity(String city) {
        Log.d(TAG, "Show current weather for: " + city);
        presenter.onRequestWeather(city);
    }

    @Override
    public void update() {
        if ((!lastUpdateV.getText().equals(presenter.setLastUpdateViewText()))) {
            cityNameV.setText(presenter.setCityName());
            currentV.setText(presenter.setTemperatureCurrentTextView());
            minV.setText(presenter.setTemperatureMinimumTextView());
            maxV.setText(presenter.setTemperatureMaximumTextView());
            weatherV.setText(presenter.setWeatherDescriptionTextView());
            humidityV.setText(presenter.setHumidityViewText());
            lastUpdateV.setText(presenter.setLastUpdateViewText());
            dateV.setText(presenter.setDateViewText());
            saveCurrentData();
            makeSnack(Objects.requireNonNull(getView()), getString(R.string.update_succesful));

        } else {
            makeSnack(Objects.requireNonNull(getView()), getString(R.string.update_server_delay));
        }
    }

    @Override
    public void cancelUpdate() {
        makeSnack(Objects.requireNonNull(getView()), String.format(getString(R.string.update_in_time),String.valueOf(10 - (System.currentTimeMillis() / 1000 - last_dt) / 60)));
    }

    @Override
    public void onFailUpdate(String message) {
        makeSnack(Objects.requireNonNull(getView()), message);
    }

    private void saveCurrentData() {
        if (!sharedPreferences.getString("lastUpdateV", "").equals(String.valueOf(lastUpdateV.getText()))) {
            editor = sharedPreferences.edit();
            Log.d(TAG, "Prefs are saved");
            editor.putInt("last_dt", last_dt);
            editor.putString("current_city", current_city);

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
            Log.d(TAG, "date is same");
        }
    }

    private void loadSharedPreferences() {
        sharedPreferences = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        last_dt = sharedPreferences.getInt("last_dt", 0);
        current_city = sharedPreferences.getString("current_city", "");

        cityNameV.setText(sharedPreferences.getString("cityNameV", "Click Search Button"));
        currentV.setText(sharedPreferences.getString("currentV", ""));
        minV.setText(sharedPreferences.getString("minV", ""));
        maxV.setText(sharedPreferences.getString("maxV", ""));
        weatherV.setText(sharedPreferences.getString("weatherV", ""));
        humidityV.setText(sharedPreferences.getString("humidityV", ""));
        lastUpdateV.setText(sharedPreferences.getString("lastUpdateV", ""));
        dateV.setText(sharedPreferences.getString("dateV", ""));
    }


}



