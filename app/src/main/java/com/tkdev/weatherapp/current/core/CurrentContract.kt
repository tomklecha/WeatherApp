package com.tkdev.weatherapp.current.core

import android.widget.ImageView
import com.tkdev.weatherapp.current.data.retrofit_data_source.dto.RetrofitModel
import retrofit2.Response
import java.util.*

interface CurrentContract {

    interface View {
        fun showWeatherByCity(city: String)

        fun update()

        fun cancelUpdate()

        fun onFailUpdate(message: String)

        fun setTemperatureCurrent(value: String)

        fun setTemperatureMinimum(value: String)

        fun setTemperatureMaximum(value: String)

        fun setWeatherDescription(value: String)

        fun setHumidity(value: String)

        fun setLastUpdate(value: String)

        fun setDate(value: String)

        fun setCityName(value: String)

        fun shareWeather(booleanList: ArrayList<Boolean>): String
    }

    interface Presenter {
        fun onDestroy()

        fun bind(view: View)

        fun onRequestWeather(city: String)

        fun sendCurrentWeather(): String

        fun getWeatherIcon(imageView: ImageView)

    }

    interface Interactor {
        suspend fun getWeather(city: CurrentWeatherDomainCity): RetrofitModel

    }

    interface Repository{
        suspend fun apiRequest(city: CurrentWeatherDomainCity) : RetrofitModel
    }

}