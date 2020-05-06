package com.tkdev.weatherapp.current.bresenter

import android.widget.ImageView
import com.tkdev.weatherapp.common.core.coroutines.CoroutineDispatcherFactory
import com.tkdev.weatherapp.current.bresenter.model.ModelMapper
import com.tkdev.weatherapp.current.bresenter.model.WeatherModel
import com.tkdev.weatherapp.current.core.CurrentContract
import com.tkdev.weatherapp.current.core.WeatherDomain
import com.tkdev.weatherapp.current.core.WeatherDomainCity
import com.tkdev.weatherapp.current.core.WeatherDomainTempPrefix
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CurrentPresenter(
        private val interactor: CurrentContract.Interactor,
        private val dispatcher: CoroutineDispatcherFactory,
        private val mapper: ModelMapper
) : CurrentContract.Presenter, CoroutineScope {

    private lateinit var view: CurrentContract.View

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = dispatcher.UI + job

    override fun bind(view: CurrentContract.View) {
        this.view = view
    }

    override fun onRequestWeather(city: String, prefix: String) {
        requestData(city, prefix)
    }

    override fun onDestroy() {
        job.cancel()
    }

    private fun CoroutineScope.requestData(city: String, prefix: String) = launch(dispatcher.IO) {
        when (val result = interactor.getWeather(WeatherDomainCity(city), WeatherDomainTempPrefix(prefix))) {
            is WeatherDomain.Weather -> updateViews(mapper.toModel(result))
            is WeatherDomain.Fail -> failedUpdate(result.errorDomain.value)
        }
    }

    private fun CoroutineScope.updateViews(weather: WeatherModel) = launch(dispatcher.UI) {
        view.setCityName(weather.city.value)
        view.setTemperatureCurrent(weather.tempObject.temp.value)
        view.setTemperatureMinimum(weather.tempObject.tempMin.value)
        view.setTemperatureMaximum(weather.tempObject.tempMax.value)
        view.setHumidity(weather.humidity.value)
        view.setWeatherDescription(weather.description.value)
        view.setLastUpdate(weather.lastUpdate.value)
    }

    private fun CoroutineScope.failedUpdate(message: String) = launch(dispatcher.UI) {
        view.onFailUpdate(message)
    }

    override fun getWeatherIcon(imageView: ImageView) {
        TODO()
    }

    override fun sendCurrentWeather(): String {
        TODO()
    }
}