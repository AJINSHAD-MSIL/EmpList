package com.example.emplist.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.emplist.common.Constants
import com.example.emplist.data.models.DataX
import com.example.emplist.retrofithelper.RetrofitHelper
import kotlinx.coroutines.flow.Flow

class PagingRepository {
    fun paginguserflow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<DataX>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { UserDataPageingSource(RetrofitHelper) }
        ).flow
    }
    fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = Constants.pagesize, enablePlaceholders = false)
    }

}