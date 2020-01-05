package com.tkdev.weatherapp.tasks;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tkdev.weatherapp.model.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public class QueryWeather {


    public static final String TAG = "QueryWeather";


    public static Weather fetchCurrent(String requestUrl) {


        return extractCurrentFromJson(fetch(requestUrl));
    }

    public static List<Weather> fetchForecasts(String requestUrl) {

        return extractForecastFromJson(fetch(requestUrl));
    }

    private static String fetch(String requestUrl) {
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = httpRequest(url);
        } catch (IOException e) {
            Log.e(TAG, "Error closing input stream", e);
        }
        return jsonResponse;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
        return url;
    }

    private static String httpRequest(URL stringUrl) throws IOException {
        String jsonResponse = "";

        if (stringUrl == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) stringUrl.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readStream(inputStream);
            } else {
                Log.e(TAG, "Error response code: " + urlConnection.getResponseCode());
                return null;
            }

        } catch (IOException e) {
            Log.e(TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;

    }

    private static String readStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<Weather> extractForecastFromJson(String weatherJSON) {
        if (TextUtils.isEmpty(weatherJSON)) {
            return null;
        }

        List<Weather> forecasts = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(weatherJSON);
            JSONArray listResponseArray = baseJsonResponse.getJSONArray("list");

            for (int i = 0; i < listResponseArray.length(); i++) {
                JSONObject forecastObject = listResponseArray.getJSONObject(i);
                JSONObject mainResponse = forecastObject.getJSONObject("main");
                double temperatureCurrent = mainResponse.getDouble("temp");
                double temperatureMin = mainResponse.getDouble("temp_min");
                double temperatureMax = mainResponse.getDouble("temp_max");
                Date date = new Date( forecastObject.getLong("dt") * 1000);


                JSONArray weatherResponseArray = forecastObject.getJSONArray("weather");
                JSONObject weatherResponse = weatherResponseArray.getJSONObject(0);
                String weatherDescription = weatherResponse.getString("main");


                Weather weatherForecast = baseWeatherBuild(temperatureCurrent, temperatureMin, temperatureMax, weatherDescription);
                weatherForecast.setDayOfForecast(date);

                forecasts.add(weatherForecast);
            }


        } catch (JSONException e) {
            Log.e(TAG, "Problem parsing the forecast JSON results", e);
        }
        return forecasts;
    }


    private static Weather extractCurrentFromJson(String weatherJSON) {
        if (TextUtils.isEmpty(weatherJSON)) {
            return null;
        }


        try {
            JSONObject baseJsonResponse = new JSONObject(weatherJSON);
            Date date = new Date(baseJsonResponse.getLong("dt") * 1000);


            JSONObject mainResponse = baseJsonResponse.getJSONObject("main");
            double temperatureCurrent = mainResponse.getDouble("temp");
            double temperatureMin = mainResponse.getDouble("temp_min");
            double temperatureMax = mainResponse.getDouble("temp_max");
            int humidity = mainResponse.getInt("humidity");

            JSONArray weatherResponseArray = baseJsonResponse.getJSONArray("weather");
            JSONObject weatherResponse = weatherResponseArray.getJSONObject(0);
            String weatherDescription = weatherResponse.getString("main");

            Weather weatherCurrent = baseWeatherBuild(temperatureCurrent, temperatureMin, temperatureMax, weatherDescription);
            weatherCurrent.setHumidity(humidity);
            weatherCurrent.setDateOfLastUpdate(date);

            return weatherCurrent;

        } catch (JSONException e) {
            Log.e(TAG, "Problem parsing the forecast JSON results", e);
            return null;
        }
    }

    private static Weather baseWeatherBuild(double temperatureCurrent, double temperatureMin, double temperatureMax, String weatherDescription) {
        Weather weather = new Weather();
        weather.setTemperatureCurrent(temperatureCurrent);
        weather.setTemperatureMin(temperatureMin);
        weather.setTemperatureMax(temperatureMax);
        weather.setWeather(weatherDescription);
        return weather;
    }


}
