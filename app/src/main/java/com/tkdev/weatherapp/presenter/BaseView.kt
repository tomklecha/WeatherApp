package com.tkdev.weatherapp.presenter

interface BaseView<T> {
    fun setPresenter(presenter: T)
}