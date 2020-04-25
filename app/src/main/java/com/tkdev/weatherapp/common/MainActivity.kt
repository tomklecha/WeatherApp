package com.tkdev.weatherapp.common

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.tkdev.weatherapp.R
import com.tkdev.weatherapp.common.PreferencesVariables.Companion.current_city
import com.tkdev.weatherapp.common.RetrofitCalls.Companion.DATE_PATTERN
import com.tkdev.weatherapp.common.RetrofitCalls.Companion.datePattern
import com.tkdev.weatherapp.common.dialogs.AboutDialog
import com.tkdev.weatherapp.common.dialogs.SearchDialog
import com.tkdev.weatherapp.common.dialogs.SearchDialog.SearchDialogListener
import com.tkdev.weatherapp.common.dialogs.ShareDialog
import com.tkdev.weatherapp.common.dialogs.ShareDialog.ShareDialogListener
import com.tkdev.weatherapp.current.WeatherCurrentFragment
import com.tkdev.weatherapp.forecast.WeatherForecastFragment
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*

class MainActivity : AppCompatActivity(), SearchDialogListener, ShareDialogListener {
    private val TAG = "WeatherMainActivity"

    private lateinit var currentListener: MainContract.View
    private lateinit var forecastListener: MainContract.View
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
                currentListener.showWeatherByCity(current_city)
                forecastListener.showWeatherByCity(current_city)
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
        if (fragment is WeatherCurrentFragment) {
            currentListener = fragment
        }
        if (fragment is WeatherForecastFragment) {
            forecastListener = fragment
        }
    }

    private fun toolbarSetup() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        today_date_toolbar.text = datePattern(System.currentTimeMillis(), DATE_PATTERN)

    }

    private fun showBasicFragments() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.content_current,
                        WeatherCurrentFragment.newInstance()
                )
                .commit()
        supportFragmentManager.beginTransaction()
                .replace(R.id.content_forecast,
                        WeatherForecastFragment.newInstance()
                )
                .commit()
    }

    private fun shareWeather(booleanList: ArrayList<Boolean>) {
        val weatherIntent = Intent()
        weatherIntent.action = Intent.ACTION_SEND
        weatherIntent.putExtra(Intent.EXTRA_TEXT, currentListener.shareWeather(booleanList))
        weatherIntent.type = "text/plain"
        val shareIntent = Intent.createChooser(weatherIntent, null)
        startActivity(shareIntent)
    }

    override fun onSearchPositiveClick(city: String) {
        currentListener.showWeatherByCity(city)
        forecastListener.showWeatherByCity(city)
    }

    override fun onSearchNegativeClick(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSharePositiveClick(list: ArrayList<Boolean>) {
        shareWeather(list)
    }

    override fun onShareNegativeClick(message: String) {} // TODO !!!

    //    private boolean connectivity() {
    //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    //            if (checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED) {
    //                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    //                return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
    //                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    //            }
    //        }
    //    }

}