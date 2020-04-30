package com.tkdev.weatherapp.current.bresenter

import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.tkdev.weatherapp.current.core.CurrentContract
import com.tkdev.weatherapp.current.core.CurrentWeatherDomainCity
import com.tkdev.weatherapp.current.coroutines.CoroutineDispatcherFactory
import com.tkdev.weatherapp.current.data.retrofit_data_source.dto.RetrofitModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CurrentPresenter(
        private val interactor: CurrentContract.Interactor,
        private val dispatcher: CoroutineDispatcherFactory
) : CurrentContract.Presenter, CoroutineScope {

    private lateinit var view: CurrentContract.View
    private lateinit var weather: RetrofitModel

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = dispatcher.UI + job

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
        requestData(city)
    }

    override fun onDestroy() {

    }

    private fun CoroutineScope.requestData(city: String) = launch(dispatcher.IO) {
        weather = interactor.getWeather(CurrentWeatherDomainCity(city))
        updateViews()
    }

    private fun CoroutineScope.updateViews() = launch(dispatcher.UI) {
        view.setCityName(weather.name)
        view.setTemperatureCurrent(weather.main.temp.toString())
        view.setTemperatureMinimum(weather.main.tempMin.toString())
        view.setTemperatureMaximum(weather.main.tempMax.toString())
        view.setHumidity(weather.main.tempMax.toString())
        view.setWeatherDescription(weather.main.tempMax.toString())
    }

    override fun getWeatherIcon(imageView: ImageView) {
        return Picasso.get()
                .load(String.format("http://openweathermap.org/img/wn/%s@2x.png", weather.weather[0].icon))
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


}