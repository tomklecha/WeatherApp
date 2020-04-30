package com.tkdev.weatherapp.current.app

import com.tkdev.weatherapp.current.core.CurrentContract
import com.tkdev.weatherapp.current.bresenter.CurrentPresenter
import com.tkdev.weatherapp.current.core.CurrentInteractor
import com.tkdev.weatherapp.current.data.CurrentRepository
import com.tkdev.weatherapp.current.data.retrofit_data_source.RetrofitApi
import com.tkdev.weatherapp.current.data.retrofit_data_source.RetrofitApiDefault

class CurrentServiceLocator {

    fun getPresenter(): CurrentPresenter = CurrentPresenter(getInteractor())

    private fun getInteractor(): CurrentContract.Interactor = CurrentInteractor(getRepository())

    private fun getRepository(): CurrentContract.Repository = CurrentRepository(getApi())

    private fun getApi(): RetrofitApi = RetrofitApiDefault()
}