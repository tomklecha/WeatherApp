package com.tkdev.weatherapp.forecast.bresenter

import com.tkdev.weatherapp.common.core.coroutines.CoroutineDispatcherFactory
import com.tkdev.weatherapp.common.domain.retrofit_data_source.forecast_dto.ForecastRetrofit
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.current_prefix
import com.tkdev.weatherapp.current.core.WeatherDomain
import com.tkdev.weatherapp.forecast.core.ForecastContract
import com.tkdev.weatherapp.forecast.core.ForecastDomain
import com.tkdev.weatherapp.forecast.core.ForecastDomainCity
import com.tkdev.weatherapp.forecast.core.ForecastDomainTempPrefix
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ForecastPresenter(private val interactor: ForecastContract.Interactor,
                        private val dispatcher: CoroutineDispatcherFactory
//,
) : ForecastContract.Presenter, CoroutineScope {

    private lateinit var forecasts: ForecastRetrofit
    private lateinit var view: ForecastContract.View

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = dispatcher.UI + job


    override fun bind(view: ForecastContract.View) {
        this.view = view
    }

    override fun onDestroy() {
        job.cancel()
    }

    override fun showData() {
        loadData()
    }

    override fun onRequestWeather(city: String) {
        requestData(city)
    }

    private fun CoroutineScope.saveData(forecast: ForecastDomain.WeatherForecast) = launch(dispatcher.IO) {
        interactor.saveCurrentWeather(forecast)
    }

    private fun CoroutineScope.loadData() = launch(dispatcher.UI) {
        when (val result = interactor.loadData()) {
            is ForecastDomain.WeatherForecast -> {
                forecasts = result.listForecasts
                updateViews()
            }
            is ForecastDomain.Fail -> failedUpdate(result.errorDomain.value)
        }
    }

    private fun CoroutineScope.requestData(city: String) = launch(dispatcher.IO) {
        when (val result = interactor.getForecasts(ForecastDomainCity(city))) {
            is ForecastDomain.WeatherForecast -> {
                forecasts = result.listForecasts
                saveData(result)
                updateViews()
            }
            is ForecastDomain.Fail -> failedUpdate(result.errorDomain.value)
        }
    }

    override fun getForecastsList(): ForecastRetrofit = forecasts

    private fun CoroutineScope.updateViews() = launch(dispatcher.UI) {
        view.update()
    }

    private fun CoroutineScope.failedUpdate(message: String) = launch(dispatcher.UI) {
        view.onFailUpdate(message)
    }

}