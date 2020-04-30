package com.tkdev.weatherapp.current.data

import com.tkdev.weatherapp.current.data.retrofit_data_source.dto.RetrofitModel
import retrofit2.Response

interface DtoMapper {
    fun toModel(response: Response<RetrofitModel>): RetrofitModel
}

class DtoMapperDefault : DtoMapper {
    override fun toModel(response: Response<RetrofitModel>): RetrofitModel =
            response.body()!!
}

