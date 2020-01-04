package com.tkdev.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.tkdev.weatherapp.model.WeatherCurrent;
import com.tkdev.weatherapp.model.WeatherForecast;

import java.util.List;
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


//        WeatherCurrentTask currentTask = new WeatherCurrentTask();
//        currentTask.execute();
//        WeatherCurrent current = null;
//        try {
//            current = currentTask.get();
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        assert current != null;

        WeatherForecastTask forecastTask = new WeatherForecastTask();
        forecastTask.execute();
        List<WeatherForecast> current = null;
        try {
            current = forecastTask.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }


        currentV = findViewById(R.id.tempCurrent);
        minV = findViewById(R.id.tempMin);
        maxV = findViewById(R.id.tempMax);
        weatherV = findViewById(R.id.weatherDescripton);

        assert current != null;
        currentV.setText(String.valueOf(current.get(0).getTemperatureCurrent()));
        minV.setText(String.valueOf(current.get(3).getTemperatureMin()));
        maxV.setText(String.valueOf(current.get(6).getTemperatureMax()));
        weatherV.setText(String.valueOf(current.get(1).getWeatherDescription()));




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
//
//    WeatherCurrentTask currentTask = new WeatherCurrentTask();
//        currentTask.execute();
//                WeatherCurrent current = null;
//                try {
//                current = currentTask.get();
//                } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//                }
//
//                assert current != null;
//                System.out.println(current.getTemperatureCur());
//                System.out.println(current.getTemperatureMin());
//                System.out.println(current.getTemperatureMax());
//                System.out.println(current.getWeatherDescription());
//                System.out.println(current.getDateOfLastUpdate());
//                System.out.println(current.getHumidity());


//
//        new WeatherForecastTask().execute();
//
//        WeatherForecastTask forecastTask = new WeatherForecastTask();
//        forecastTask.execute();
//        List<WeatherForecast> forecasts = null;
//        try {
//            forecasts = forecastTask.get();
//        } catch (ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//        }


//
//        if (forecasts !=null){
//        for (WeatherForecast forecast: forecasts){
//            System.out.println("***********************************");
//            double tempCur = forecast.getTemperatureCurrent();
//            double tempMin = forecast.getTemperatureMin();
//            double tempMax = forecast.getTemperatureMax();
//            System.out.println(forecast.getDayOfForecast());
//            System.out.println("***********************************");
//            System.out.println(tempCur + "!!!!!!");
//            System.out.println("***********************************");
//            System.out.println(tempMin);
//            System.out.println("***********************************");
//            System.out.println(tempMax);
//            System.out.println("***********************************");
//            System.out.println(forecast.getWeatherDescription());
//            System.out.println("***********************************");
//        }}