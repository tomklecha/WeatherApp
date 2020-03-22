package com.tkdev.weatherapp.presenter.current

import com.tkdev.weatherapp.model.current_weather.WeatherRetrofit
import com.tkdev.weatherapp.presenter.MainContract
import com.tkdev.weatherapp.presenter.MainContract.APIListener
import com.tkdev.weatherapp.presenter.MainContract.Presenter
import com.tkdev.weatherapp.repository.WeatherRetrofitImpl
import com.tkdev.weatherapp.utils.PreferencesVariables
import com.tkdev.weatherapp.utils.PreferencesVariables.current_city
import com.tkdev.weatherapp.utils.PreferencesVariables.last_dt
import com.tkdev.weatherapp.utils.RetrofitCalls
import retrofit2.Response

class CurrentPresenterImpl(
        private var view: MainContract.View)
    : Presenter, APIListener, CurrentPresenter {

    private val model: MainContract.Model
    private var weather: WeatherRetrofit?

    init {
        model = WeatherRetrofitImpl()
        weather = WeatherRetrofit()
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
        weather = response.body()
        last_dt = weather?.dt!!
        current_city = weather!!.name
        view.update()
    }

    override fun onFailureResponse(responseMessage: String) {
        view.onFailUpdate(responseMessage)
    }

    // TestView text changers
    override fun setTemperatureCurrentTextView(): String {
        return RetrofitCalls.temperaturePrefix(weather!!.main!!.temp!!)
    }

    override fun setTemperatureMinimumTextView(): String {
        return RetrofitCalls.temperaturePrefix(weather!!.main!!.tempMin!!)
    }

    override fun setTemperatureMaximumTextView(): String {
        return RetrofitCalls.temperaturePrefix(weather!!.main!!.tempMax!!)
    }

    override fun setWeatherDescriptionTextView(): String {
        return weather!!.weather!![0].main!!
    }

    override fun setHumidityViewText(): String {
        return weather!!.main!!.humidity.toString() + RetrofitCalls.HUMIDITY_SYMBOL
    }

    override fun setLastUpdateViewText(): String {
        return RetrofitCalls.datePattern(weather!!.dt!! + weather!!.timezone!!, RetrofitCalls.LAST_UPDATE_PATTERN)
    }

    override fun setDateViewText(): String {
        return RetrofitCalls.datePattern((System.currentTimeMillis() / 1000).toInt(), RetrofitCalls.DATE_PATTERN)
    }

    override fun setCityName(): String {
        return weather!!.name!!
    }

    override fun sendCurrentWeather(): String {
        val weatherShare = StringBuilder()
        weatherShare.append(PreferencesVariables.current_city)
                .append(" ")
                .append(weather!!.main!!.temp)
        return weatherShare.toString()
    }


}