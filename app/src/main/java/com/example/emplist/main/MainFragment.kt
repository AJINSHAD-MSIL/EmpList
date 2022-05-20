package com.example.emplist.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.emplist.common.Constants
import com.example.emplist.common.Resources
import com.example.emplist.common.Toasts
import com.example.emplist.common.onclickInterface
import com.example.emplist.databinding.MainFragmentBinding
import com.example.emplist.data.models.DataX
import com.example.emplist.main.viewFactory.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MainFragment : Fragment(), onclickInterface {

    lateinit var userAdapter: UserAdapter
    private lateinit var binding: MainFragmentBinding
    lateinit var adapter: PagingAdapter

    private val viewModel: MainViewModel by viewModels() { ViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater)
        setUpViews()
        fetchUserData()
        fetchUserDetails()
        Constants.isloaded = true
        return binding.root
    }

    private fun fetchUserDetails()
    {
        lifecycleScope.launch {
           viewModel.useCase.invoke().flowOn(Dispatchers.Main)
                .collect {
                    when (it) {
                        is Resources.Success-> {
                            it.data?.let { response ->
                                viewModel.list.postValue(response)
                            }
                        }
                        is Resources.Error ->
                        {
                            Toasts().toast(requireContext(),"Please check your network connections")
                        }
                        is Resources.Loading ->
                        {
                            Toasts().toast(requireContext(),"Please wait")
                        }
                    }
                }
        }
    }



    private fun fetchUserData() {
        lifecycleScope.launch {
            viewModel.fetchuserdatas().distinctUntilChanged().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun setUpViews() {
        adapter = context?.let { PagingAdapter(it, this) }!!
        userAdapter = context?.let { UserAdapter(it, this) }!!
        viewModel.list.value?.let { userAdapter.setUserList(it) }
        binding.userLists.layoutManager = GridLayoutManager(context, 2)
        val concatAdapter = ConcatAdapter(userAdapter,adapter)
        binding.userLists.adapter = concatAdapter
    }

    override fun onclick(data: DataX) {
        val action = MainFragmentDirections.actionHomeFragmentToUserFragment(data)
        findNavController(this.binding.root).navigate(action)
    }


}