package com.tkdev.weatherapp.forecast.bresenter

import com.tkdev.weatherapp.common.core.MainContract
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.current_city
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.last_dt
import com.tkdev.weatherapp.common.domain.WeatherRetrofitImpl
import com.tkdev.weatherapp.forecast.model.ForecastRetrofit
import retrofit2.Response

class ForecastPresenter(private var view: MainContract.View) : MainContract.Presenter, ForecastContract, MainContract.APIForecastListener {

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

    override fun bind(view: MainContract.View) {
        TODO("Not yet implemented")
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