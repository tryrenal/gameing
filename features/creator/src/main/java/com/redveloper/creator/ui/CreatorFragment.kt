package com.redveloper.creator.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.redveloper.core.vo.Resource
import com.redveloper.creator.R
import com.redveloper.creator.core.domain.model.Creator
import kotlinx.android.synthetic.main.fragment_creator.*
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class CreatorFragment : Fragment() {

    val creatorViewModel: CreatorViewModel by viewModel()
    private val creatorAdapter = CreatorAdapter()

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
            creatorViewModel.getAllCreator().observe(viewLifecycleOwner, { data ->
                if (data != null) {
                    when (data) {
                        is Resource.Loading -> {
                        }
                        is Resource.Success -> {
                            Timber.i(data.data.toString())
                            data.data?.let { showingData(it) }
                        }
                        is Resource.Error -> {
                            Timber.e(data.message)
                        }
                    }
                }
            })
        }
    }

    private fun showingData(data: PagingData<Creator>){
        lifecycleScope.launch {
            creatorAdapter.submitData(data)
        }
        with(rv_creator){
            adapter = creatorAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

}