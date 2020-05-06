package com.tkdev.weatherapp.forecast.bresenter

import com.tkdev.weatherapp.common.core.coroutines.CoroutineDispatcherFactory
import com.tkdev.weatherapp.common.domain.retrofit_data_source.forecast_dto.ForecastRetrofit
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

    override fun onRequestWeather(city: String, prefix: String) {
        requestData(city, prefix)
    }

    private fun CoroutineScope.requestData(city: String, prefix: String) = launch(dispatcher.IO) {
        when (val result = interactor.getForecasts(ForecastDomainCity(city), ForecastDomainTempPrefix(prefix))) {
            is ForecastDomain.Weather ->
//                view.onFailUpdate(result.city.value)
            view.update()
//                updateViews(mapper.toModel(result))
            is ForecastDomain.Fail -> view.onFailUpdate(result.errorDomain.value)
        }
    }

    override fun getForecastsList(): ForecastRetrofit = forecasts
}

//if (current_city == "") {
//            current_city = "london"
//            model.getWeather(this, current_city)
//        } else if (last_dt <= System.currentTimeMillis() / 1000 - 600
//                ||
//                current_city != city) {
//            model.getWeather(this, city)
//        } else {
//            view.cancelUpdate()
//        }