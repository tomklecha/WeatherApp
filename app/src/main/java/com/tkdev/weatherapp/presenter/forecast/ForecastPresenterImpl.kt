package com.tkdev.weatherapp.presenter.forecast

import com.tkdev.weatherapp.model.forecast_weather.ForecastRetrofit
import com.tkdev.weatherapp.presenter.MainContract

import com.tkdev.weatherapp.repository.WeatherRetrofitImpl
import com.tkdev.weatherapp.utils.PreferencesVariables
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
        if (PreferencesVariables.current_city == "") {
            PreferencesVariables.current_city = "london"
            model.getWeather(this, PreferencesVariables.current_city)
        } else if (PreferencesVariables.last_dt <= System.currentTimeMillis() / 1000 - 600
                ||
                PreferencesVariables.current_city != city) {
            model.getWeather(this, city)
        } else {
            view.cancelUpdate()
        }
    }
}