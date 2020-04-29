package com.tkdev.weatherapp.forecast

import com.tkdev.weatherapp.common.MainContract
import com.tkdev.weatherapp.forecast.model.ForecastRetrofit

interface ForecastPresenter : MainContract {

    fun getForecasts() : ForecastRetrofit
}