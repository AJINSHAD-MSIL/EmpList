package com.example.emplist.paging

import androidx.paging.PagingSource
import com.example.emplist.common.Constants
import com.example.emplist.data.models.DataX
import com.example.emplist.retrofithelper.QuotesApi
import com.example.emplist.retrofithelper.RetrofitHelper
import retrofit2.HttpException
import java.io.IOException


class UserDataPageingSource(private val retrofithelper: RetrofitHelper) :
    PagingSource<Int, DataX>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataX> {
        return try {

            var position = params.key ?: Constants.startingindex
            var response = retrofithelper.getInstance().create(QuotesApi::class.java)
                .getQuotes2(position, params.loadSize).body()!!.data
            LoadResult.Page(
                data = response,
                prevKey = if (position == Constants.startingindex) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
             LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}