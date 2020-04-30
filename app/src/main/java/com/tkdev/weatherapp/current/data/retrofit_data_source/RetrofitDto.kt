package com.tkdev.weatherapp.current.data.retrofit_data_source

sealed class RetrofitDto {

    object Valid: RetrofitDto()

    object Invalid: RetrofitDto()
}