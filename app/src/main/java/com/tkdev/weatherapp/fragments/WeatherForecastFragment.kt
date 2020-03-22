package com.tkdev.weatherapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tkdev.weatherapp.R
import com.tkdev.weatherapp.adapters.ForecastAdapter
import com.tkdev.weatherapp.presenter.MainContract
import com.tkdev.weatherapp.presenter.MainContract.Presenter
import com.tkdev.weatherapp.presenter.forecast.ForecastPresenterImpl
import kotlinx.android.synthetic.main.fragment_weather_forecast.*
import java.util.*

class WeatherForecastFragment : Fragment(), MainContract.View {

    private  val TAG = "WeatherForecastFragment"

    private lateinit var presenter: ForecastPresenterImpl
    private lateinit var adapter: ForecastAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_weather_forecast, container, false)
        setPresenter(ForecastPresenterImpl(this))
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        forecast_recycler_view.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

    }

    override fun setPresenter(presenter: Presenter) {
        this.presenter = presenter as ForecastPresenterImpl
    }

    override fun showWeatherByCity(city: String) {
        Log.d(TAG, "Show current forecast for: $city")
        presenter.onRequestWeather(city)
    }

    override fun update() {
        adapter = ForecastAdapter(presenter.forecasts, context)
        forecast_recycler_view.adapter = adapter
    }

    override fun cancelUpdate() {}
    override fun onFailUpdate(message: String) {}
    override fun shareWeather(booleanList: ArrayList<Boolean>): String {
        return ""
    }
}

