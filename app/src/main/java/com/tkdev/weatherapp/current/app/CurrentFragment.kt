package com.tkdev.weatherapp.current.app

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.tkdev.weatherapp.R
import com.tkdev.weatherapp.current.bresenter.CurrentPresenter
import com.tkdev.weatherapp.current.core.CurrentContract
import kotlinx.android.synthetic.main.fragment_weather_current.*

class CurrentFragment :
        Fragment(R.layout.fragment_weather_current),
        CurrentContract.View {

    private lateinit var presenter: CurrentPresenter

    companion object {
        fun newInstance(): CurrentFragment = CurrentFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val serviceLocator = CurrentServiceLocator()
        presenter = serviceLocator.getPresenter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.bind(this)
    }

    override fun showWeatherByCity(city: String, prefix: String) {
        presenter.onRequestWeather(city, prefix)
    }

    override fun onFailUpdate(message: String) {
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }

    override fun setTemperatureCurrent(value: String) {
        city_temp_current.text = value
    }

    override fun setTemperatureMinimum(value: String) {
        city_temp_min.text = value
    }

    override fun setTemperatureMaximum(value: String) {
        city_temp_max.text = value
    }

    override fun setWeatherDescription(value: String) {
        city_weather.text = value
    }

    override fun setHumidity(value: String) {
        city_humidity.text = value
    }

    override fun setLastUpdate(value: String) {
        city_hour_update.text = value
    }

    override fun setCityName(value: String) {
        city_name.text = value
    }
}

