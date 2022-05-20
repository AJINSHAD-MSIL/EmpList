package com.example.emplist.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.emplist.common.Constants
import com.example.emplist.databinding.UserfullviewBinding
import com.example.emplist.data.models.DataX
import com.squareup.picasso.Picasso

class UserFullView : Fragment() {
    private lateinit var userfullviewBinding: UserfullviewBinding
    val list: MutableLiveData<List<DataX>> = MutableLiveData()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userfullviewBinding = UserfullviewBinding.inflate(layoutInflater)
        val data = arguments?.get("data") as DataX
        Constants.isloaded = false
        userfullviewBinding.lastName.text = data.last_name
        userfullviewBinding.userName.text = data.first_name
        Picasso.get().load(data.avatar).into(userfullviewBinding.userImage)
        userfullviewBinding.userId.text = data.id.toString()
        userfullviewBinding.userEmail.text = data.email
        userfullviewBinding.btn.setOnClickListener {
            val action = UserFullViewDirections.actionUserfragmentToHomeFragment()
            findNavController().navigate(action)

        }
        return userfullviewBinding.root

    }
}