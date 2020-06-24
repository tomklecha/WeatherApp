package com.tkdev.weatherapp.forecast.core

import com.tkdev.weatherapp.common.domain.retrofit_data_source.forecast_dto.ForecastRetrofit
import com.tkdev.weatherapp.current.core.WeatherDomain


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

        fun showData()

    }

    interface Interactor {
        suspend fun getForecasts(city: ForecastDomainCity): ForecastDomain

        suspend fun loadData(): ForecastDomain

        fun saveCurrentWeather(forecast: ForecastDomain.WeatherForecast)
    }

    interface Repository{
        suspend fun apiRequest(city: ForecastDomainCity) : ForecastDomain

        suspend fun loadSharedPreferenceDomain(): ForecastDomain

        fun saveSharedPreferences(forecast: ForecastDomain.WeatherForecast)
    }

}