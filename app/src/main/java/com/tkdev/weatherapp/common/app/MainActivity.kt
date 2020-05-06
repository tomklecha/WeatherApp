package com.tkdev.weatherapp.common.app

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.tkdev.weatherapp.R
import com.tkdev.weatherapp.common.app.dialogs.AboutDialog
import com.tkdev.weatherapp.common.app.dialogs.SearchDialog
import com.tkdev.weatherapp.common.app.dialogs.SearchDialog.SearchDialogListener
import com.tkdev.weatherapp.common.app.dialogs.ShareDialog
import com.tkdev.weatherapp.common.app.dialogs.ShareDialog.ShareDialogListener
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.current_city
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.todays_day
import com.tkdev.weatherapp.common.util.PreferencesVariables.Companion.current_prefix
import com.tkdev.weatherapp.current.app.CurrentFragment
import com.tkdev.weatherapp.current.core.CurrentContract
import com.tkdev.weatherapp.forecast.app.ForecastFragment
import com.tkdev.weatherapp.forecast.core.ForecastContract
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*

class MainActivity : AppCompatActivity(), SearchDialogListener, ShareDialogListener {

    private lateinit var currentListener: CurrentContract.View
    private lateinit var forecastListener: ForecastContract.View

    private lateinit var dialog: DialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbarSetup()
        showBasicFragments()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_refresh_weather -> {
                currentListener.showWeatherByCity(current_city, current_prefix)
                forecastListener.showWeatherByCity(current_city, current_prefix)
                true
            }
            R.id.menu_app_bar_search -> {
                dialog = SearchDialog()
                dialog.show(supportFragmentManager, "SearchDialog")
                true
            }
            R.id.menu_share -> {
                dialog = ShareDialog()
                dialog.show(supportFragmentManager, "ShareDialog")
                true
            }
            R.id.menu_about_dev -> {
                dialog = AboutDialog()
                dialog.show(supportFragmentManager, "AboutDialog")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onAttachFragment(fragment: Fragment) {
        when (fragment) {
            is CurrentFragment -> currentListener = fragment
            is ForecastFragment -> forecastListener = fragment
        }
    }

    private fun toolbarSetup() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        today_date_toolbar.text = todays_day
    }

    private fun showBasicFragments() {
        supportFragmentManager.beginTransaction().replace(R.id.content_current,
                CurrentFragment.newInstance()).commit()
        supportFragmentManager.beginTransaction()
                .replace(R.id.content_forecast,
                        ForecastFragment.newInstance()
                )
                .commit()
    }

    private fun shareWeather(booleanList: ArrayList<Boolean>) {
        TODO()
    }

    override fun onSearchPositiveClick(city: String) {
        currentListener.showWeatherByCity(city, current_prefix)
        forecastListener.showWeatherByCity(city, current_prefix)
    }

    override fun onSearchNegativeClick(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSharePositiveClick(list: ArrayList<Boolean>) {
        TODO()
    }

    override fun onShareNegativeClick(message: String) {
        TODO()
    }

}