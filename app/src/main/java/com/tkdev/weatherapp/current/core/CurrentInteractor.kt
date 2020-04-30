package com.tkdev.weatherapp.current.core

import com.tkdev.weatherapp.current.bresenter.CurrentPresenter
import com.tkdev.weatherapp.current.data.retrofit_data_source.dto.RetrofitModel

class CurrentInteractor(
        private val repository: CurrentContract.Repository
) : CurrentContract.Interactor {



    override suspend fun getWeather(city: CurrentWeatherDomainCity): RetrofitModel {
        return repository.apiRequest(city)
    }


}