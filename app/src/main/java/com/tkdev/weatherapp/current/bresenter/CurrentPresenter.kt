package com.tkdev.weatherapp.current.bresenter

import android.widget.ImageView
import com.tkdev.weatherapp.current.core.CurrentContract
import com.tkdev.weatherapp.current.core.CurrentWeatherDomain
import com.tkdev.weatherapp.current.core.CurrentWeatherDomainCity
import com.tkdev.weatherapp.current.coroutines.CoroutineDispatcherFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CurrentPresenter(
        private val interactor: CurrentContract.Interactor,
        private val dispatcher: CoroutineDispatcherFactory
) : CurrentContract.Presenter, CoroutineScope {

    private lateinit var view: CurrentContract.View
    private lateinit var weather: CurrentWeatherDomain.Weather

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = dispatcher.UI + job

    override fun bind(view: CurrentContract.View) {
        this.view = view
    }

    override fun onRequestWeather(city: String) {
        requestData(city)
    }

    override fun onDestroy() {

    }

    private fun CoroutineScope.requestData(city: String) = launch(dispatcher.IO) {
        when (val result = interactor.getWeather(CurrentWeatherDomainCity(city))) {
            is CurrentWeatherDomain.Weather -> updateViews(result)
            is CurrentWeatherDomain.Fail -> view.onFailUpdate(result.errorDomain.value)
        }
    }

    private fun CoroutineScope.updateViews(weather: CurrentWeatherDomain.Weather) = launch(dispatcher.UI) {
        view.setCityName(weather.city.value)
        view.setTemperatureCurrent(weather.temp.value)
        view.setTemperatureMinimum(weather.tempMin.value)
        view.setTemperatureMaximum(weather.tempMax.value)
        view.setHumidity(weather.humidity.value)
        view.setWeatherDescription(weather.description.value)
    }

    override fun getWeatherIcon(imageView: ImageView) {
//        return Picasso.get()
//                .load(String.format("http://openweathermap.org/img/wn/%s@2x.png", weather.weather[0].icon))
//                .into(imageView)
        TODO()
    }

    override fun sendCurrentWeather(): String {
        TODO()
//        val weatherShare = StringBuilder()
//        weatherShare.append(current_city)
//                .append(" ")
//                .append(weather.main.temp)
//        return weatherShare.toString()
    }


}