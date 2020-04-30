package com.tkdev.weatherapp.current.data

import com.tkdev.weatherapp.current.core.CurrentContract
import com.tkdev.weatherapp.current.core.CurrentInteractor
import com.tkdev.weatherapp.current.core.CurrentWeatherDomainCity
import com.tkdev.weatherapp.current.data.retrofit_data_source.RetrofitApi
import com.tkdev.weatherapp.current.data.retrofit_data_source.dto.RetrofitModel
import retrofit2.Response


class CurrentRepository(private val api: RetrofitApi)
    : CurrentContract.Repository, CurrentContract.APIListener {

    private lateinit var weather: RetrofitModel
    private var listener: CurrentContract.DomainListener = CurrentInteractor(this)

    override fun apiRequest(city: CurrentWeatherDomainCity) = try {
        api.getCurrentWeather(city.value)
    } catch (e: Exception) {
        TODO()
    }

    override fun onSuccessResponse(response: Response<RetrofitModel>) {
        weather = response.body()!!
        listener.getApiResponse(weather)
//        PreferencesVariables.last_dt = weather.dt
//        PreferencesVariables.current_city = weather.name

    }

    override fun onFailureResponse(responseMessage: String) {
//       
    }
//        }catch (e: Exception){
//        CurrentWeatherDomain.Fail(CurrentWeatherErrorDomain(e.message.toString()))
}


