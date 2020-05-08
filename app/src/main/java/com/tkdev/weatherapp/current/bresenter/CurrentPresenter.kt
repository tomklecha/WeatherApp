package com.tkdev.weatherapp.current.bresenter

import android.widget.ImageView
import com.tkdev.weatherapp.common.core.coroutines.CoroutineDispatcherFactory
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.current_prefix
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
    private lateinit var weatherModel: WeatherModel

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
        job.cancel()
    }

    private fun CoroutineScope.requestData(city: String) = launch(dispatcher.IO) {
        when (val result = interactor.getWeather(WeatherDomainCity(city))) {
            is WeatherDomain.Weather ->
            { weatherModel = mapper.toModel(result)
                updateViews()
                saveData(result)
            }
            is WeatherDomain.Fail -> failedUpdate(result.errorDomain.value)
        }
    }

    private fun CoroutineScope.saveData(weather: WeatherDomain.Weather) = launch(dispatcher.IO) {
        interactor.saveCurrentWeather(weather)
    }

    private fun CoroutineScope.updateViews() = launch(dispatcher.UI) {
        view.setCityName(weatherModel.city.value)
        view.setTemperatureCurrent(weatherModel.tempObject.temp.value)
        view.setTemperatureMinimum(weatherModel.tempObject.tempMin.value)
        view.setTemperatureMaximum(weatherModel.tempObject.tempMax.value)
        view.setHumidity(weatherModel.humidity.value)
        view.setWeatherDescription(weatherModel.description.value)
        view.setLastUpdate(weatherModel.lastUpdate.value)
    }

    private fun CoroutineScope.failedUpdate(message: String) = launch(dispatcher.UI) {
        view.onFailUpdate(message)
    }

    override fun getWeatherIcon(imageView: ImageView) {
        TODO()
    }

    override fun loadData() {
        weatherModel = mapper.toModel(interactor.loadData())
        saveData(interactor.loadData())
        updateViews()
    }

    override fun sendCurrentWeather(): String {
        TODO()
    }
}