package com.redveloper.home.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.redveloper.core.vo.Resource
import com.redveloper.home.R
import com.redveloper.home.core.domain.model.Game
import com.redveloper.home.ui.detail.DetailHomeActivity
import com.redveloper.home.ui.list.adapter.HomeAdapter
import com.redveloper.home.ui.list.adapter.IHomeAdapter
import com.redveloper.home.utils.idGame
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : Fragment(), IHomeAdapter {

    val homeViewModel: HomeViewModel by viewModel()
    private val homeAdapter = HomeAdapter()

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
                            Timber.i(data.data.toString())
                            data.data?.let { showDataGame(it) }
                        }
                        is Resource.Error -> {
                            Timber.e(data.message)
                        }
                    }
                }
            })
        }
    }

    private fun showDataGame(data: PagingData<Game>) {
        lifecycleScope.launch {
            homeAdapter.submitData(data)
        }
        rv_game.adapter = homeAdapter
        homeAdapter.setCallback(this)
    }

    override fun onItemHomeClicked(id: Int) {
        val intent = Intent(requireContext(), DetailHomeActivity::class.java)
        intent.putExtra(idGame, id)
        requireContext().startActivity(intent)
    }
}