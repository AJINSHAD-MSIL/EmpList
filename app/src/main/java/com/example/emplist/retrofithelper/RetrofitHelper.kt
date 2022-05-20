package com.example.emplist.retrofithelper

import com.example.emplist.common.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}