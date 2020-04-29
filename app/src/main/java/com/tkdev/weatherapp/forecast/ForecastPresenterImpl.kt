package com.tkdev.weatherapp.forecast

import com.tkdev.weatherapp.common.MainContract
import com.tkdev.weatherapp.common.PreferencesVariables.Companion.current_city
import com.tkdev.weatherapp.common.PreferencesVariables.Companion.last_dt
import com.tkdev.weatherapp.common.WeatherRetrofitImpl
import com.tkdev.weatherapp.forecast.model.ForecastRetrofit
import retrofit2.Response

class ForecastPresenterImpl(private var view: MainContract.View) : MainContract.Presenter, ForecastPresenter, MainContract.APIForecastListener {

    private lateinit var forecasts: ForecastRetrofit
    private val model: MainContract.Model

    init {
        model = WeatherRetrofitImpl()
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

    override fun getForecasts(): ForecastRetrofit {
        return forecasts
    }
}