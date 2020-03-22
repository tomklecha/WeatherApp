package com.tkdev.weatherapp.presenter

import com.tkdev.weatherapp.model.current_weather.WeatherRetrofit
import com.tkdev.weatherapp.model.forecast_weather.ForecastRetrofit
import retrofit2.Response
import java.util.*

interface MainContract {
    interface Model {
        fun getWeather(listener: APIListener, city: String)
        fun getWeather(listener: APIForecastListener, city: String)
    }

    interface Presenter : BasePresenter {
        fun onRequestWeather(city: String)
    }

    interface View : BaseView<Presenter> {
        fun showWeatherByCity(city: String)
        fun update()
        fun cancelUpdate()
        fun onFailUpdate(message: String)
        fun shareWeather(booleanList: ArrayList<Boolean>): String
    }

    interface APIListener {
        fun onSuccessResponse(response: Response<WeatherRetrofit>)
        fun onFailureResponse(responseMessage: String)
    }

    interface APIForecastListener {
        fun onSuccessResponse(response: Response<ForecastRetrofit>)
        fun onFailureResponse(responseMessage: String)
    }
}