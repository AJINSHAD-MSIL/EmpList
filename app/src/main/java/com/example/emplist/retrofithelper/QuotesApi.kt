package com.example.emplist.retrofithelper

import com.example.emplist.data.models.Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesApi {
    @GET("users")
    suspend fun getQuotes() : Response<Data>
    @GET("users?page=2")
    suspend fun getQuotes2(@Query("page") page: Int, @Query("total_pages") size: Int) : Response<Data>

}