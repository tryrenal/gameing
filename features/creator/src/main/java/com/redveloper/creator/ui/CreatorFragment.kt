package com.redveloper.creator.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.redveloper.core.vo.Resource
import com.redveloper.creator.R
import org.koin.android.viewmodel.ext.android.viewModel

class CreatorFragment : Fragment() {

    val creatorViewModel: CreatorViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_creator, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            creatorViewModel.getAllCreator().observe(this, Observer { data ->
                if (data != null) {
                    when (data) {
                        is Resource.Loading -> {
                        }
                        is Resource.Success -> {
                            Log.i("dataCreator", data.data.toString())
                        }
                        is Resource.Error -> {
                            Log.i("errorCreator", data.message.toString())
                        }
                    }
                }
            })
        }
    }

}