package com.tkdev.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.tkdev.weatherapp.model.Weather;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    static final String WEATHER_CURRENT_REQUEST = "https://api.openweathermap.org/data/2.5/weather?id=";
    static final String WEATHER_FORECAST_REQUEST = "https://api.openweathermap.org/data/2.5/forecast?id=";
    static final String WEATHER_CITY_ID = "2643743";
    static final String WEATHER_API_PREFIX = "&APPID=";
    static final String WEATHER_API_KEY = "5b08b54ce198509d241991110864cab4";
    static final String WEATHER_TEMPERATURE = "metric";
    static final String WEATHER_TEMPERATURE_PREFIX = "&units=";

    private static final String TAG = "WeatherMainActivity";
    private TextView currentV;
    private TextView minV;
    private TextView maxV;
    private TextView weatherV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // test WeatherCurrentTask
        WeatherCurrentTask currentTask = new WeatherCurrentTask();
        currentTask.execute();
        Weather current = null;
        try {
            current = currentTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        assert current != null;

        // testWeatherForecastTask
//        WeatherForecastTask forecastTask = new WeatherForecastTask();
//        forecastTask.execute();
//        List<WeatherForecast> current = null;
//        try {
//            current = forecastTask.get();
//        } catch (ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//        }
//        assert current != null;


        currentV = findViewById(R.id.tempCurrent);
        minV = findViewById(R.id.tempMin);
        maxV = findViewById(R.id.tempMax);
        weatherV = findViewById(R.id.weatherDescripton);

        currentV.setText(String.valueOf(current.getTemperatureCurrent()));
        minV.setText(String.valueOf(current.getTemperatureMin()));
        maxV.setText(String.valueOf(current.getTemperatureMax()));
        weatherV.setText(current.getWeather());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}