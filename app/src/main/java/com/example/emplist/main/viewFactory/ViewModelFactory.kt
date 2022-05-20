package com.example.emplist.main.viewFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.emplist.di.Daggerusercomponent
import com.example.emplist.domain.UseCase
import com.example.emplist.main.MainViewModel
import javax.inject.Inject

class ViewModelFactory : ViewModelProvider.Factory {

    @Inject
    lateinit var usecase : UseCase
    init {
        Daggerusercomponent.create().Inject(this)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(usecase) as T
    }
}