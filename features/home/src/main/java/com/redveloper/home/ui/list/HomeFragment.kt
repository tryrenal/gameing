@file:Suppress("DEPRECATION")

package com.redveloper.home.ui.list

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.presentation.scrollLeft
import com.redveloper.home.R
import com.redveloper.home.core.domain.model.Game
import com.redveloper.home.ui.detail.DetailHomeActivity
import com.redveloper.home.ui.list.adapter.HomeAdapter
import com.redveloper.home.ui.list.adapter.IHomeAdapter
import com.redveloper.home.utils.idGame
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel


@ExperimentalCoroutinesApi
@FlowPreview
class HomeFragment : Fragment(), IHomeAdapter{

    val homeViewModel: HomeViewModel by viewModel()
    private lateinit var progressDialog: ProgressDialog

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
            progressDialog = ProgressDialog(requireContext())

            getDataGamePager()
            getFavoriteGame()
            searchGame()
            setGreeting()
            rv_game.scrollLeft(img_ilustration_game, parent)
        }
    }

    private fun searchGame(){
       search_game.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
           override fun onQueryTextSubmit(query: String?): Boolean {
               return true
           }

           override fun onQueryTextChange(newText: String?): Boolean {
               homeViewModel.query.value = newText
               return true
           }
       })
    }

    private fun getDataGamePager(){
        lifecycleScope.launch {
            homeViewModel.game.distinctUntilChanged().collectLatest {
                showDataGame(it)
            }
        }
    }

    private fun getFavoriteGame(){
        homeViewModel.getFavoriteGame().observe(viewLifecycleOwner, Observer { data ->
            showDataGameFavorite(data)
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setGreeting() {
        greeting.text = "Hello\n${homeViewModel.setGreeting()}"
    }

    private fun showDataGame(data: PagingData<Game>) {
        val homeAdapter = HomeAdapter()
        lifecycleScope.launch {
            homeAdapter.submitData(data)
        }
        with(rv_game) {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
        homeAdapter.setCallback(this)
    }

    private fun showDataGameFavorite(data: PagingData<Game>) {
        val homeAdapter = HomeAdapter()
        lifecycleScope.launch {
            homeAdapter.submitData(data)
        }
        with(rv_game_favorite){
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
        homeAdapter.setCallback(this)
    }

    override fun onItemHomeClicked(id: Int) {
        val intent = Intent(requireContext(), DetailHomeActivity::class.java)
        intent.putExtra(idGame, id)
        requireContext().startActivity(intent)
    }
}
