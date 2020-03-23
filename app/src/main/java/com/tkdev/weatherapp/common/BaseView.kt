package com.tkdev.weatherapp.common

interface BaseView<T> {
    fun setPresenter(presenter: T)
}