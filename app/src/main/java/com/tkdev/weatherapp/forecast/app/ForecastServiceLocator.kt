package com.tkdev.weatherapp.forecast.app

import com.tkdev.weatherapp.common.core.coroutines.CoroutineDispatcherFactory
import com.tkdev.weatherapp.common.core.coroutines.CoroutineDispatcherFactoryDefault
import com.tkdev.weatherapp.common.domain.retrofit_data_source.RetrofitApiDefault
import com.tkdev.weatherapp.common.domain.retrofit_data_source.RetrofitForecastApi
import com.tkdev.weatherapp.forecast.bresenter.ForecastPresenter
import com.tkdev.weatherapp.forecast.core.ForecastContract
import com.tkdev.weatherapp.forecast.core.ForecastInteractor
import com.tkdev.weatherapp.forecast.data.ForecastDtoMapper
import com.tkdev.weatherapp.forecast.data.ForecastDtoMapperDefault
import com.tkdev.weatherapp.forecast.data.ForecastReopsitory


class ForecastServiceLocator {

    fun getPresenter(): ForecastPresenter = ForecastPresenter(getInteractor(), getCoroutineDispatcher()
//            , getModelMapper()
    )

    private fun getInteractor(): ForecastContract.Interactor = ForecastInteractor(getRepository())

    private fun getRepository(): ForecastContract.Repository = ForecastReopsitory(getApi()
            , getDomainMapper()
    )

    private fun getApi(): RetrofitForecastApi = RetrofitApiDefault()

    private fun getDomainMapper(): ForecastDtoMapper = ForecastDtoMapperDefault()

    private fun getCoroutineDispatcher(): CoroutineDispatcherFactory = CoroutineDispatcherFactoryDefault()

//    private fun getModelMapper(): ModelMapper = ModelMapperDefault()
}