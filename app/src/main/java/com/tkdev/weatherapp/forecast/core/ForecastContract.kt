package com.tkdev.weatherapp.forecast.core

import com.tkdev.weatherapp.common.domain.retrofit_data_source.forecast_dto.ForecastRetrofit


interface ForecastContract {

    interface View {
        fun showWeatherByCity(city: String)

        fun onFailUpdate(message: String)

        fun update()
    }

    interface Presenter {
        fun onDestroy()

        fun bind(view: View)

        fun onRequestWeather(city: String)

        fun getForecastsList() : ForecastRetrofit
    }

    interface Interactor {
        suspend fun getForecasts(city: ForecastDomainCity): ForecastDomain
    }

    interface Repository{
        suspend fun apiRequest(city: ForecastDomainCity) : ForecastDomain
    }

}