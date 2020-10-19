package com.redveloper.home.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.redveloper.core.vo.Resource
import com.redveloper.home.R
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null){
            homeViewModel.games().observe(viewLifecycleOwner, Observer { data ->
                if (data != null){
                    when(data) {
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            Log.i("dataGame", data.data.toString())
                        }
                        is Resource.Error -> {
                            Log.i("dataGame", data.message.toString())
                        }
                    }
                }
            })
        }
    }
}