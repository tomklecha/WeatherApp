package com.tkdev.weatherapp.presenter.forecast

import com.tkdev.weatherapp.model.forecast_weather.ForecastRetrofit
import com.tkdev.weatherapp.presenter.MainContract

import com.tkdev.weatherapp.repository.WeatherRetrofitImpl
import com.tkdev.weatherapp.utils.PreferencesVariables
import com.tkdev.weatherapp.utils.PreferencesVariables.current_city
import com.tkdev.weatherapp.utils.PreferencesVariables.last_dt
import retrofit2.Response

class ForecastPresenterImpl(private var view: MainContract.View) : MainContract.Presenter, ForecastPresenter, MainContract.APIForecastListener {

    var forecasts: ForecastRetrofit
    private val model: MainContract.Model

    init {
        model = WeatherRetrofitImpl()
        forecasts = ForecastRetrofit()
    }

    override fun onSuccessResponse(response: Response<ForecastRetrofit>) {
        forecasts = response.body()!!
        view.update()
    }

    override fun onFailureResponse(responseMessage: String) {
//        view.onFailUpdate(responseMessage);
    }

    override fun onDestroy() {

    }

    override fun onRequestWeather(city: String) {
        if (current_city == "") {
            current_city = "london"
            model.getWeather(this, current_city)
        } else if (last_dt <= System.currentTimeMillis() / 1000 - 600
                ||
                current_city != city) {
            model.getWeather(this, city)
        } else {
            view.cancelUpdate()
        }
    }
}