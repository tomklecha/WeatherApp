package com.tkdev.weatherapp.current.app

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.tkdev.weatherapp.R
import com.tkdev.weatherapp.common.domain.RetrofitCalls
import com.tkdev.weatherapp.common.util.PreferencesVariables
import com.tkdev.weatherapp.current.bresenter.CurrentPresenter
import com.tkdev.weatherapp.current.core.CurrentContract
import kotlinx.android.synthetic.main.fragment_weather_current.*
import kotlinx.android.synthetic.main.toolbar.*


class CurrentFragment :
        Fragment(R.layout.fragment_weather_current),
        CurrentContract.View {

    private val TAG = "WeatherCurrentFragment"
    private lateinit var presenter: CurrentPresenter
    private lateinit var sharedPreferences: SharedPreferences

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedPreferences = activity?.getSharedPreferences("mypref", Context.MODE_PRIVATE)!!
        loadSharedPreferences()
    }

    override fun showWeatherByCity(city: String) {
        presenter.onRequestWeather(city)
    }

    override fun cancelUpdate() {
        RetrofitCalls.makeSnack((view), String.format(getString(R.string.update_in_time), (10 - (System.currentTimeMillis() / 1000 - PreferencesVariables.last_dt) / 60).toString()))
    }

    override fun onFailUpdate(message: String) { RetrofitCalls.makeSnack((view), message) }

    override fun setTemperatureCurrent(value: String) { city_temp_current.text = value }

    override fun setTemperatureMinimum(value: String) { city_temp_min.text = value }

    override fun setTemperatureMaximum(value: String) { city_temp_max.text = value }

    override fun setWeatherDescription(value: String) { city_weather.text = value }

    override fun setHumidity(value: String) { city_humidity.text = value }

    override fun setLastUpdate(value: String) { city_hour_update.text = value }

    override fun setCityName(value: String) { city_name.text = value }

    override fun shareWeather(booleanList: ArrayList<Boolean>): String {
        val shares = ArrayList<String>()
        val stringBuilder = StringBuilder()
        stringBuilder.append(" Weather conditions in ${PreferencesVariables.current_city}: ")
        shares.add(sharedPreferences.getString(PreferencesVariables.CURRENT_TEMP_VIEW, ""))
        shares.add(sharedPreferences.getString(PreferencesVariables.WEATHER_DESCRIPTION_VIEW, ""))
        shares.add(sharedPreferences.getString(PreferencesVariables.LAST_UPDATE_DATE_VIEW, ""))
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
        }
    }
}


//            RetrofitCalls.makeSnack((view), getString(R.string.update_succesful))
//        } else {
//            RetrofitCalls.makeSnack((view), getString(R.string.update_server_delay))
//        }


