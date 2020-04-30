package com.tkdev.weatherapp.common.util

class PreferencesVariables {
    companion object {
        var last_dt = 0
        var current_city = ""

        const val summer_time = 3600
        const val LAST_DT_STRING = "last_dt"
        const val CURRENT_CITY_STRING = "current_city"
        const val CITY_NAME_VIEW = "cityNameView"
        const val CURRENT_TEMP_VIEW = "currentTempView"
        const val MIN_TEMP_VIEW = "minTempView"
        const val MAX_TEMP_VIEW = "maxTempView"
        const val WEATHER_DESCRIPTION_VIEW = "weatherDescriptionView"
        const val HUMIDITY_VIEW = "humidityView"
        const val LAST_UPDATE_DATE_VIEW = "lastUpdateDateView"
        const val TODAY_DAY_VIEW = "todayDayView"
    }
}