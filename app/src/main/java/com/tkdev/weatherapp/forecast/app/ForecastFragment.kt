package com.tkdev.weatherapp.forecast.app

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.tkdev.weatherapp.R
import com.tkdev.weatherapp.forecast.bresenter.ForecastPresenter
import com.tkdev.weatherapp.forecast.core.ForecastContract
import kotlinx.android.synthetic.main.fragment_weather_forecast.*

class ForecastFragment : Fragment(), ForecastContract.View {

    private lateinit var presenter: ForecastPresenter
    private lateinit var adapter: ForecastAdapter

    companion object {
        fun newInstance(): ForecastFragment = ForecastFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val serviceLocator = ForecastServiceLocator()
        presenter = serviceLocator.getPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
        = inflater.inflate(R.layout.fragment_weather_forecast, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.bind(this)
        forecast_recycler_view.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }

    override fun showWeatherByCity(city: String) {
        presenter.onRequestWeather(city)
    }

    override fun onFailUpdate(message: String) {
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }

    override fun update() {
        adapter = ForecastAdapter(presenter.getForecastsList())
        forecast_recycler_view.adapter = adapter
    }
//
//    override fun cancelUpdate() {}
//    override fun onFailUpdate(message: String) {}
//    override fun shareWeather(booleanList: ArrayList<Boolean>): String {
//        return ""
//    }
}

