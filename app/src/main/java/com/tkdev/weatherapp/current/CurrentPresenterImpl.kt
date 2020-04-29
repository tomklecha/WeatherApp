package com.tkdev.weatherapp.current

import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.tkdev.weatherapp.common.MainContract
import com.tkdev.weatherapp.common.MainContract.APIListener
import com.tkdev.weatherapp.common.MainContract.Presenter
import com.tkdev.weatherapp.common.PreferencesVariables.Companion.current_city
import com.tkdev.weatherapp.common.PreferencesVariables.Companion.last_dt
import com.tkdev.weatherapp.common.PreferencesVariables.Companion.summer_time
import com.tkdev.weatherapp.common.RetrofitCalls
import com.tkdev.weatherapp.common.WeatherRetrofitImpl
import com.tkdev.weatherapp.current.model.WeatherRetrofit
import retrofit2.Response

class CurrentPresenterImpl(
        private var view: MainContract.View)
    : Presenter, APIListener, CurrentPresenter {

    private lateinit var weather: WeatherRetrofit
    private val model: MainContract.Model

    init {
        model = WeatherRetrofitImpl()
    }

    override fun onRequestWeather(city: String) {
        if (current_city == "") {
            current_city = "london"
            model.getWeather(this, current_city)
        } else if (last_dt <= System.currentTimeMillis() / 1000 - 600
                ||
                current_city != city) {
            model.getWeather(this, city)
        } else {
            view.cancelUpdate()
        }
    }

    override fun onDestroy() {

    }

    override fun onSuccessResponse(response: Response<WeatherRetrofit>) {
        weather = response.body()!!
        last_dt = weather.dt
        current_city = weather.name
        view.update()
    }

    override fun onFailureResponse(responseMessage: String) {
        view.onFailUpdate(responseMessage)
    }

    // TestView text changers
    override fun setTemperatureCurrentTextView(): String {
        return RetrofitCalls.temperaturePrefix(weather.main.temp)
    }

    override fun setTemperatureMinimumTextView(): String {
        return RetrofitCalls.temperaturePrefix(weather.main.tempMin)
    }

    override fun setTemperatureMaximumTextView(): String {
        return RetrofitCalls.temperaturePrefix(weather.main.tempMax)
    }

    override fun setWeatherDescriptionTextView(): String {
        return weather.weather[0].main
    }

    override fun setHumidityViewText(): String {
        return weather.main.humidity.toString() + RetrofitCalls.HUMIDITY_SYMBOL
    }

    override fun setLastUpdateViewText(): String {
        return RetrofitCalls.datePattern((weather.dt + weather.timezone - summer_time).toLong() * 1000, RetrofitCalls.LAST_UPDATE_PATTERN)
    }

    override fun setDateViewText(): String {
        return RetrofitCalls.datePattern((System.currentTimeMillis()), RetrofitCalls.DATE_PATTERN)
    }

    override fun setCityName(): String {
        return weather.name
    }

    override fun sendCurrentWeather(): String {
        val weatherShare = StringBuilder()
        weatherShare.append(current_city)
                .append(" ")
                .append(weather.main.temp)
        return weatherShare.toString()
    }

    override fun setWeatherIcon(imageView: ImageView) {
        return Picasso.get()
                .load(String.format("http://openweathermap.org/img/wn/%s@2x.png", weather.weather[0].icon))
                .into(imageView)
    }


}