package com.example.emplist.main
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.emplist.data.models.DataX
import com.example.emplist.domain.UseCase
import com.example.emplist.paging.PagingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainViewModel @Inject constructor(var useCase: UseCase) : ViewModel() {
    val list: MutableLiveData<List<DataX>> = MutableLiveData()

fun fetchuserdatas(): Flow<PagingData<DataX>> {
    return PagingRepository().paginguserflow()
        .map { it.map { it } }
        .cachedIn(viewModelScope)


}
}