package com.tkdev.weatherapp.current.bresenter

import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.current_city
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.summer_time
import com.tkdev.weatherapp.common.domain.RetrofitCalls
import com.tkdev.weatherapp.current.core.CurrentContract
import com.tkdev.weatherapp.current.core.CurrentWeatherDomainCity
import com.tkdev.weatherapp.current.data.retrofit_data_source.dto.RetrofitModel

class CurrentPresenter(private val interactor: CurrentContract.Interactor)
    : CurrentContract.Presenter, CurrentContract.PresenterListener {

    private lateinit var view: CurrentContract.View

    override fun bind(view: CurrentContract.View) {
        this.view = view
    }

    //        if (current_city == "") {
//            current_city = "london"
//            model.getWeather(this, current_city)
//        } else if (last_dt <= System.currentTimeMillis() / 1000 - 600
//                ||
//                current_city != city) {
//        } else {
//            view.cancelUpdate()
//        }
    override fun onRequestWeather(city: String) {
        interactor.getWeather(CurrentWeatherDomainCity(city))
//        view.update()
    }

    override fun onDestroy() {

    }

    // TestView text changers
    override fun getTemperatureCurrent(): String = interactor.setTemperatureCurrent()

    override fun getTemperatureMinimum(): String = interactor.setTemperatureMinimum()

    override fun getTemperatureMaximum(): String = interactor.setTemperatureMaximum()

    override fun getWeatherDescription(): String = interactor.setWeatherDescription()

    override fun getHumidity(): String = interactor.setHumidity()

    override fun getLastUpdate(): String = interactor.setLastUpdate()

    override fun getDate(): String = interactor.setDate()

    override fun getCityName(): String = interactor.setCityName()

    override fun getWeatherIcon(imageView: ImageView) {
        return Picasso.get()
                .load(String.format("http://openweathermap.org/img/wn/%s@2x.png", interactor.setIcon()))
                .into(imageView)
    }

    override fun sendCurrentWeather(): String {
        TODO()
//        val weatherShare = StringBuilder()
//        weatherShare.append(current_city)
//                .append(" ")
//                .append(weather.main.temp)
//        return weatherShare.toString()
    }

    override fun getDomainResponse() {
        getTemperatureCurrent()
    }


}