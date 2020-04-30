package com.tkdev.weatherapp.current.data

import com.tkdev.weatherapp.current.core.CurrentContract
import com.tkdev.weatherapp.current.core.CurrentWeatherDomainCity
import com.tkdev.weatherapp.current.data.retrofit_data_source.RetrofitApi
import com.tkdev.weatherapp.current.data.retrofit_data_source.dto.RetrofitModel
import retrofit2.Response


class CurrentRepository(
        private val api: RetrofitApi,
        private val dto: DtoMapper)
    : CurrentContract.Repository {

    override suspend fun apiRequest(city: CurrentWeatherDomainCity): RetrofitModel
      =  dto.toModel(api.getCurrentWeather(city.value))

}


