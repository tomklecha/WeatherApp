package com.tkdev.weatherapp.current.core

class CurrentInteractor(
        private val repository: CurrentContract.Repository
) : CurrentContract.Interactor {


    override suspend fun getWeather(city: WeatherDomainCity, prefix: WeatherDomainTempPrefix): WeatherDomain {
        return repository.apiRequest(city, prefix)
    }

// TODO() implement interactions with API like -> city, lang, temp prefix


    //        if (current_city == "") {
//            current_city = "london"
//            model.getWeather(this, current_city)
//        } else if (last_dt <= System.currentTimeMillis() / 1000 - 600
//                ||
//                current_city != city) {
//        } else {
//            view.cancelUpdate()
//        }
}