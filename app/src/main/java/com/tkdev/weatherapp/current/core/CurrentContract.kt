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

        fun shareWeather(booleanList: ArrayList<Boolean>): String
    }

    interface Presenter {
        fun onDestroy()

        fun bind(view: View)

        fun onRequestWeather(city: String)

        fun getTemperatureCurrent(): String

        fun getTemperatureMinimum(): String

        fun getTemperatureMaximum(): String

        fun getWeatherDescription(): String

        fun getHumidity(): String

        fun getLastUpdate(): String

        fun getDate(): String

        fun getCityName(): String

        fun sendCurrentWeather(): String

        fun getWeatherIcon(imageView: ImageView)

    }

    interface Interactor {
        fun getWeather(city: CurrentWeatherDomainCity)

        fun setTemperatureCurrent(): String

        fun setTemperatureMinimum(): String

        fun setTemperatureMaximum(): String

        fun setWeatherDescription(): String

        fun setHumidity(): String

        fun setLastUpdate(): String

        fun setDate(): String

        fun setCityName(): String

        fun setIcon(): String

        fun sendCurrentWeather(): String


    }

    interface PresenterListener{
        fun getDomainResponse()
    }

    interface DomainListener {
        fun getApiResponse(response: RetrofitModel)
    }

    interface Repository{
        fun apiRequest(city: CurrentWeatherDomainCity)
    }

    interface APIListener {
        fun onSuccessResponse(response: Response<RetrofitModel>)
        fun onFailureResponse(responseMessage: String)

    }
}