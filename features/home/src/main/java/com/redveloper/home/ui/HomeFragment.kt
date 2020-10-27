package com.redveloper.home.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.redveloper.core.vo.Resource
import com.redveloper.home.R
import com.redveloper.home.core.domain.model.Game
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    val homeViewModel: HomeViewModel by viewModel()
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            homeViewModel.games().observe(viewLifecycleOwner, { data ->
                if (data != null) {
                    when (data) {
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            data.data?.let { showDataGame(it) }
                        }
                        is Resource.Error -> {
                            Log.i("dataGame", data.message.toString())
                        }
                    }
                }
            })
        }
    }

    private fun showDataGame(data: List<Game>) {
        homeAdapter = HomeAdapter(data)
        with(rv_game) {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}