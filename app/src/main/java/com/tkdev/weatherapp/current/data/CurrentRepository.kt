package com.tkdev.weatherapp.current.data

import com.tkdev.weatherapp.current.core.*
import com.tkdev.weatherapp.current.data.retrofit_data_source.RetrofitApi

class CurrentRepository(
        private val api: RetrofitApi,
        private val dto: DtoMapper)
    : CurrentContract.Repository {

    override suspend fun apiRequest(city: WeatherDomainCity, prefix: WeatherDomainTempPrefix): WeatherDomain = try {
        dto.toDomain(api.getCurrentWeather(city.value, prefix.value), prefix.value)
    }catch (e: NullPointerException){
        WeatherDomain.Fail(WeatherErrorDomain(e.message.toString()))
    } catch (e: Exception) {
        WeatherDomain.Fail(WeatherErrorDomain(e.message.toString()))
    }
}


