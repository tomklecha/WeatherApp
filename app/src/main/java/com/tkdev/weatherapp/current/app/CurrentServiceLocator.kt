package com.tkdev.weatherapp.current.app

import com.tkdev.weatherapp.common.core.coroutines.CoroutineDispatcherFactory
import com.tkdev.weatherapp.common.core.coroutines.CoroutineDispatcherFactoryDefault
import com.tkdev.weatherapp.common.domain.retrofit_data_source.RetrofitApiDefault
import com.tkdev.weatherapp.common.domain.retrofit_data_source.RetrofitCurrentApi
import com.tkdev.weatherapp.current.bresenter.CurrentPresenter
import com.tkdev.weatherapp.current.bresenter.model.ModelMapper
import com.tkdev.weatherapp.current.bresenter.model.ModelMapperDefault
import com.tkdev.weatherapp.current.core.CurrentContract
import com.tkdev.weatherapp.current.core.CurrentInteractor
import com.tkdev.weatherapp.current.data.CurrentDtoMapper
import com.tkdev.weatherapp.current.data.CurrentDtoMapperDefault
import com.tkdev.weatherapp.current.data.CurrentRepository

class CurrentServiceLocator {

    fun getPresenter(): CurrentPresenter = CurrentPresenter(getInteractor(), getCoroutineDispatcher(), getModelMapper())

    private fun getInteractor(): CurrentContract.Interactor = CurrentInteractor(getRepository())

    private fun getRepository(): CurrentContract.Repository = CurrentRepository(getApi(), getDomainMapper())

    private fun getApi(): RetrofitCurrentApi = RetrofitApiDefault()

    private fun getDomainMapper(): CurrentDtoMapper = CurrentDtoMapperDefault()

    private fun getCoroutineDispatcher(): CoroutineDispatcherFactory = CoroutineDispatcherFactoryDefault()

    private fun getModelMapper(): ModelMapper = ModelMapperDefault()
}