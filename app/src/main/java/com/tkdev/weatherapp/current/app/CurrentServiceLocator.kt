package com.tkdev.weatherapp.current.app

import com.tkdev.weatherapp.current.bresenter.CurrentPresenter
import com.tkdev.weatherapp.current.bresenter.model.ModelMapper
import com.tkdev.weatherapp.current.bresenter.model.ModelMapperDefault
import com.tkdev.weatherapp.current.core.CurrentContract
import com.tkdev.weatherapp.current.core.CurrentInteractor
import com.tkdev.weatherapp.current.core.coroutines.CoroutineDispatcherFactory
import com.tkdev.weatherapp.current.core.coroutines.CoroutineDispatcherFactoryDefault
import com.tkdev.weatherapp.current.data.CurrentRepository
import com.tkdev.weatherapp.current.data.DtoMapper
import com.tkdev.weatherapp.current.data.DtoMapperDefault
import com.tkdev.weatherapp.current.data.retrofit_data_source.RetrofitApi
import com.tkdev.weatherapp.current.data.retrofit_data_source.RetrofitApiDefault

class CurrentServiceLocator {

    fun getPresenter(): CurrentPresenter = CurrentPresenter(getInteractor(), getCoroutineDispatcher(), getModelMapper())

    private fun getInteractor(): CurrentContract.Interactor = CurrentInteractor(getRepository())

    private fun getRepository(): CurrentContract.Repository = CurrentRepository(getApi(), getDomainMapper())

    private fun getApi(): RetrofitApi = RetrofitApiDefault()

    private fun getDomainMapper(): DtoMapper = DtoMapperDefault()

    private fun getCoroutineDispatcher(): CoroutineDispatcherFactory = CoroutineDispatcherFactoryDefault()

    private fun getModelMapper(): ModelMapper = ModelMapperDefault()
}