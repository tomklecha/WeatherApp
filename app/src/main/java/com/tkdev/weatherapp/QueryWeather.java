package com.tkdev.weatherapp;

import android.text.TextUtils;
import android.util.Log;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class QueryWeather {


    public static final String TAG = "QueryWeather";

    public static Weather fetchCurrent(String requestUrl) {


        return extractCurrentFromJson(fetch(requestUrl));
    }

    public static List<Weather> fetchForecasts(String requestUrl) {

        return extractForecastFromJson(fetch(requestUrl));
    }

    private static String fetch(String requestUrl){
        URL url = createUrl(requestUrl);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
            JSONArray featureArray = baseJsonResponse.getJSONArray("list");

            for (int i = 0; i < featureArray.length(); i++) {
                JSONObject currentForecast = featureArray.getJSONObject(i);
                JSONObject properties = currentForecast.getJSONObject("main");
                double temperatureCurrent = properties.getDouble("temp");
                double temperatureMin = properties.getDouble("temp_min");
                double temperatureMax = properties.getDouble("temp_max");
                String weatherDay = currentForecast.getString("dt_txt");

                JSONArray weatherArray = currentForecast.getJSONArray("weather");
                JSONObject propertiesWeather = weatherArray.getJSONObject(0);
                String weatherDescription = propertiesWeather.getString("description");


                Weather weatherForecast = baseWeatherBuild(temperatureCurrent, temperatureMin, temperatureMax, weatherDescription);
                weatherForecast.setDayOfForecast(weatherDay);

                Log.d(TAG, weatherForecast.toString());
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
            JSONObject currentWeather = baseJsonResponse.getJSONObject("main");
            double temperatureCurrent = currentWeather.getDouble("temp");
            double temperatureMin = currentWeather.getDouble("temp_min");
            double temperatureMax = currentWeather.getDouble("temp_max");
            int humidity = currentWeather.getInt("humidity");
            Date date = new Date();
            date.setTime((long)baseJsonResponse.getInt("dt")*1000);

            JSONArray weatherArray = baseJsonResponse.getJSONArray("weather");
            JSONObject propertiesWeather = weatherArray.getJSONObject(0);
            String weatherDescription = propertiesWeather.getString("description");

            Weather weatherCurrent = baseWeatherBuild(temperatureCurrent, temperatureMin, temperatureMax, weatherDescription);
            weatherCurrent.setHumidity(humidity);

            Log.d(TAG, weatherCurrent.toString());
            return weatherCurrent;

        } catch (JSONException e) {
            Log.e(TAG, "Problem parsing the forecast JSON results", e);
            return null;
        }
    }

    private static Weather baseWeatherBuild(double temperatureCurrent, double temperatureMin, double temperatureMax, String weatherDescription){
        Weather weather = new Weather();
        weather.setTemperatureCurrent(temperatureCurrent);
        weather.setTemperatureMin(temperatureMin);
        weather.setTemperatureMax(temperatureMax);
        weather.setWeather(weatherDescription);
        return weather;
    }




}
