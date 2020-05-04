package com.tkdev.weatherapp.forecast.bresenter

import com.tkdev.weatherapp.common.core.MainContract
import com.tkdev.weatherapp.forecast.model.ForecastRetrofit

interface ForecastContract {

    fun getForecasts() : ForecastRetrofit
}