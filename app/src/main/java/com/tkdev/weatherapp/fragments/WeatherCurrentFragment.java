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

import java.util.ArrayList;
import java.util.Objects;

import static com.tkdev.weatherapp.utils.PreferencesVariables.CITY_NAME_VIEW;
import static com.tkdev.weatherapp.utils.PreferencesVariables.CURRENT_CITY_STRING;
import static com.tkdev.weatherapp.utils.PreferencesVariables.CURRENT_TEMP_VIEW;
import static com.tkdev.weatherapp.utils.PreferencesVariables.HUMIDITY_VIEW;
import static com.tkdev.weatherapp.utils.PreferencesVariables.LAST_DT_STRING;
import static com.tkdev.weatherapp.utils.PreferencesVariables.LAST_UPDATE_DATE_VIEW;
import static com.tkdev.weatherapp.utils.PreferencesVariables.MAX_TEMP_VIEW;
import static com.tkdev.weatherapp.utils.PreferencesVariables.MIN_TEMP_VIEW;
import static com.tkdev.weatherapp.utils.PreferencesVariables.TODAY_DAY_VIEW;
import static com.tkdev.weatherapp.utils.PreferencesVariables.WEATHER_DESCRIPTION_VIEW;
import static com.tkdev.weatherapp.utils.PreferencesVariables.current_city;
import static com.tkdev.weatherapp.utils.PreferencesVariables.last_dt;
import static com.tkdev.weatherapp.utils.RetrofitCalls.makeSnack;

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
        if ((!lastUpdateV.getText().equals(presenter.setLastUpdateViewText())) || !cityNameV.getText().equals(presenter.setCityName())) {
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
        makeSnack(Objects.requireNonNull(getView()), String.format(getString(R.string.update_in_time), String.valueOf(10 - (System.currentTimeMillis() / 1000 - last_dt) / 60)));
    }

    @Override
    public void onFailUpdate(String message) {
        makeSnack(Objects.requireNonNull(getView()), message);
    }

    @Override
    public String shareWeather(ArrayList<Boolean> booleanList) {
        ArrayList<String> shares = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(current_city).append(" weather conditions: ");
        shares.add(sharedPreferences.getString("currentV", ""));
        shares.add(sharedPreferences.getString("weatherV", ""));
        shares.add(sharedPreferences.getString("lastUpdateV", ""));
        for (int i = 0; i < shares.size(); i++) {
            if (booleanList.get(i)) {
                stringBuilder.append(shares.get(i));
                stringBuilder.append(" ,");
            }
        }

        return stringBuilder.toString();
    }

    private void saveCurrentData() {
        if (!sharedPreferences.getString(LAST_UPDATE_DATE_VIEW, "").equals(String.valueOf(lastUpdateV.getText()))) {
            editor = sharedPreferences.edit();
            Log.d(TAG, "Prefs are saved");
            editor.putInt(LAST_DT_STRING, last_dt);
            editor.putString(CURRENT_CITY_STRING, current_city);

            editor.putString(CITY_NAME_VIEW, String.valueOf(cityNameV.getText()));
            editor.putString(CURRENT_TEMP_VIEW, String.valueOf(currentV.getText()));
            editor.putString(MIN_TEMP_VIEW, String.valueOf(minV.getText()));
            editor.putString(MAX_TEMP_VIEW, String.valueOf(maxV.getText()));
            editor.putString(WEATHER_DESCRIPTION_VIEW, String.valueOf(weatherV.getText()));
            editor.putString(HUMIDITY_VIEW, String.valueOf(humidityV.getText()));
            editor.putString(LAST_UPDATE_DATE_VIEW, String.valueOf(lastUpdateV.getText()));
            editor.putString(TODAY_DAY_VIEW, String.valueOf(dateV.getText()));
            editor.apply();
        } else {
            Log.d(TAG, "date is same");
        }
    }

    private void loadSharedPreferences() {
        sharedPreferences = getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        last_dt = sharedPreferences.getInt(LAST_DT_STRING, 0);
        current_city = sharedPreferences.getString(CURRENT_CITY_STRING, "");

        cityNameV.setText(sharedPreferences.getString(CITY_NAME_VIEW, ""));
        currentV.setText(sharedPreferences.getString(CURRENT_TEMP_VIEW, ""));
        minV.setText(sharedPreferences.getString(MIN_TEMP_VIEW, ""));
        maxV.setText(sharedPreferences.getString(MAX_TEMP_VIEW, ""));
        weatherV.setText(sharedPreferences.getString(WEATHER_DESCRIPTION_VIEW, ""));
        humidityV.setText(sharedPreferences.getString(HUMIDITY_VIEW, ""));
        lastUpdateV.setText(sharedPreferences.getString(LAST_UPDATE_DATE_VIEW, ""));
        dateV.setText(sharedPreferences.getString(TODAY_DAY_VIEW, ""));
    }


}



