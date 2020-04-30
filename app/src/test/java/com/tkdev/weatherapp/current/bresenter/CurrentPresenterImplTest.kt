package com.tkdev.weatherapp.current.bresenter

import com.tkdev.weatherapp.common.core.MainContract
import com.tkdev.weatherapp.current.data.retrofit_data_source.dto.RetrofitModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

internal class CurrentPresenterImplTest {


    @MockK
    private lateinit var view: MainContract.View

    @MockK
    private lateinit var weatherRetrofit: RetrofitModel

    @MockK
    private lateinit var model: MainContract.Model

    @InjectMockKs
    private lateinit var presenter: CurrentPresenter

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun `GIVEN , WHEN , THEN model `() {
        //GIVEN

        //WHEN

        //THEN
    }

//    @Test
//    fun `GIVEN city, WHEN onRequestWeather, THEN model getWeather`() {
//        //GIVEN
//        val city = "Hogwards"
//
//        //WHEN
//        presenter.onRequestWeather(city)
//
//        //THEN
//        verify { model.getWeather(presenter, city) }
//    }
//
//    @Test
//    fun `GIVEN responseString , WHEN onFailureResponse, THEN view on fail update `() {
//        //GIVEN
//        val message = "trololo"
//
//        //WHEN
//        presenter.onFailureResponse(message)
//
//        //THEN
//        verify { view.onFailUpdate(message) }
//    }

}