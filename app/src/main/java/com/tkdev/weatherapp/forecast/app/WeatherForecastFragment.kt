package com.tkdev.weatherapp.forecast.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tkdev.weatherapp.R
import com.tkdev.weatherapp.common.core.MainContract
import com.tkdev.weatherapp.common.core.MainContract.Presenter
import com.tkdev.weatherapp.forecast.bresenter.ForecastPresenter
import kotlinx.android.synthetic.main.fragment_weather_forecast.*
import java.util.*

class WeatherForecastFragment : Fragment() {

    private val TAG = "WeatherForecastFragment"

    private lateinit var presenter: ForecastPresenter
    private lateinit var adapter: ForecastAdapter

    companion object {
        fun newInstance(): WeatherForecastFragment = WeatherForecastFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weather_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setPresenter(ForecastPresenter(this))
        forecast_recycler_view.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }

//    override fun setPresenter(presenter: Presenter) {
//        this.presenter = presenter as ForecastPresenter
//    }
//
//    override fun showWeatherByCity(city: String) {
//        presenter.onRequestWeather(city)
//    }
//
//    override fun update() {
//        adapter = ForecastAdapter(presenter.getForecasts())
//        forecast_recycler_view.adapter = adapter
//    }
//
//    override fun cancelUpdate() {}
//    override fun onFailUpdate(message: String) {}
//    override fun shareWeather(booleanList: ArrayList<Boolean>): String {
//        return ""
//    }
}

