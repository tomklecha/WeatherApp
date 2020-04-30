package com.tkdev.weatherapp.common.core

import com.tkdev.weatherapp.current.data.retrofit_data_source.dto.RetrofitModel
import com.tkdev.weatherapp.forecast.model.ForecastRetrofit
import retrofit2.Response
import java.util.*

interface MainContract {

    interface Model {
        fun getWeather(listener: APIListener, city: String)
        fun getWeather(listener: APIForecastListener, city: String)
    }

    interface Presenter {
        fun onDestroy()
        fun bind(view: View)
        fun onRequestWeather(city: String)
    }

    interface View {
        fun showWeatherByCity(city: String)
        fun update()
        fun cancelUpdate()
        fun onFailUpdate(message: String)
        fun shareWeather(booleanList: ArrayList<Boolean>): String
    }

    interface APIListener {
        fun onSuccessResponse(response: Response<RetrofitModel>)
        fun onFailureResponse(responseMessage: String)
    }

    interface APIForecastListener {
        fun onSuccessResponse(response: Response<ForecastRetrofit>)
        fun onFailureResponse(responseMessage: String)
    }
}