package com.tkdev.weatherapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tkdev.weatherapp.R
import com.tkdev.weatherapp.presenter.MainContract
import com.tkdev.weatherapp.presenter.MainContract.Presenter
import com.tkdev.weatherapp.presenter.current.CurrentPresenterImpl
import com.tkdev.weatherapp.utils.PreferencesVariables
import com.tkdev.weatherapp.utils.RetrofitCalls
import kotlinx.android.synthetic.main.fragment_weather_current.*


class WeatherCurrentFragment : Fragment(), MainContract.View {

    private val TAG = "WeatherCurrentFragment"
    private lateinit var presenter: CurrentPresenterImpl
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        fun newInstance(): WeatherCurrentFragment = WeatherCurrentFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_weather_current, container, false)
        setPresenter(CurrentPresenterImpl(this))

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedPreferences = activity?.getSharedPreferences("mypref", Context.MODE_PRIVATE)!!
        loadSharedPreferences()
    }

    override fun setPresenter(presenter: Presenter) {
        this.presenter = presenter as CurrentPresenterImpl
    }

    override fun showWeatherByCity(city: String) {
        Log.d(TAG, "Show current weather for: $city")
        presenter.onRequestWeather(city)
    }

    override fun update() {
        if (city_hour_update.text != presenter.setLastUpdateViewText() || city_name.text != presenter.setCityName()) {
            city_name.text = presenter.setCityName()
            city_temp_current.text = presenter.setTemperatureCurrentTextView()
            city_temp_min.text = presenter.setTemperatureMinimumTextView()
            city_temp_max.text = presenter.setTemperatureMaximumTextView()
            city_weather.text = presenter.setWeatherDescriptionTextView()
            city_humidity.text = presenter.setHumidityViewText()
            city_hour_update.text = presenter.setLastUpdateViewText()
//            today_date_toolbar.text = presenter.setDateViewText()
            saveCurrentData()
            RetrofitCalls.makeSnack((view), getString(R.string.update_succesful))
        } else {
            RetrofitCalls.makeSnack((view), getString(R.string.update_server_delay))
        }
    }

    override fun cancelUpdate() {
        RetrofitCalls.makeSnack((view), String.format(getString(R.string.update_in_time), (10 - (System.currentTimeMillis() / 1000 - PreferencesVariables.last_dt) / 60).toString()))
    }

    override fun onFailUpdate(message: String) {
        RetrofitCalls.makeSnack((view), message)
    }

    override fun shareWeather(booleanList: ArrayList<Boolean>): String {
        val shares = ArrayList<String>()
        val stringBuilder = StringBuilder()
        stringBuilder.append(PreferencesVariables.current_city).append(" weather conditions: ")
        shares.add(sharedPreferences.getString("currentV", ""))
        shares.add(sharedPreferences.getString("weatherV", ""))
        shares.add(sharedPreferences.getString("lastUpdateV", ""))
        for (i in shares.indices) {
            if (booleanList[i]) {
                stringBuilder.append(shares[i])
                stringBuilder.append(" ,")
            }
        }
        return stringBuilder.toString()
    }

    private fun saveCurrentData() {
        if (sharedPreferences.getString(PreferencesVariables.LAST_UPDATE_DATE_VIEW, "") != city_hour_update!!.text.toString()) {
            with(sharedPreferences.edit()) {
                putInt(PreferencesVariables.LAST_DT_STRING, PreferencesVariables.last_dt)
                putString(PreferencesVariables.CURRENT_CITY_STRING, PreferencesVariables.current_city)
                putString(PreferencesVariables.CITY_NAME_VIEW, city_name.text.toString())
                putString(PreferencesVariables.CURRENT_TEMP_VIEW, city_temp_current.text.toString())
                putString(PreferencesVariables.MIN_TEMP_VIEW, city_temp_min.text.toString())
                putString(PreferencesVariables.MAX_TEMP_VIEW, city_temp_max.text.toString())
                putString(PreferencesVariables.WEATHER_DESCRIPTION_VIEW, city_weather.text.toString())
                putString(PreferencesVariables.HUMIDITY_VIEW, city_humidity.text.toString())
                putString(PreferencesVariables.LAST_UPDATE_DATE_VIEW, city_hour_update.text.toString())
//                putString(PreferencesVariables.TODAY_DAY_VIEW, today_date_toolbar.text.toString())
                commit()
            }
            Log.d(TAG, "Prefs are saved")

        } else {
            Log.d(TAG, "date is same")
        }
    }

    private fun loadSharedPreferences() {
        with(sharedPreferences) {
            PreferencesVariables.last_dt = getInt(PreferencesVariables.LAST_DT_STRING, 0)
            PreferencesVariables.current_city = getString(PreferencesVariables.CURRENT_CITY_STRING, "")
            city_name.text = getString(PreferencesVariables.CITY_NAME_VIEW, "")
            city_temp_current.text = getString(PreferencesVariables.CURRENT_TEMP_VIEW, "")
            city_temp_min.text = getString(PreferencesVariables.MIN_TEMP_VIEW, "")
            city_temp_max.text = getString(PreferencesVariables.MAX_TEMP_VIEW, "")
            city_weather.text = getString(PreferencesVariables.WEATHER_DESCRIPTION_VIEW, "")
            city_humidity.text = getString(PreferencesVariables.HUMIDITY_VIEW, "")
            city_hour_update.text = getString(PreferencesVariables.LAST_UPDATE_DATE_VIEW, "")
//            today_date_toolbar.text = getString(PreferencesVariables.TODAY_DAY_VIEW, "")
        }
    }
}