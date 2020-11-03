package com.redveloper.home.ui.list.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.redveloper.home.core.domain.model.Game

class HomeAdapter : PagingDataAdapter<Game, HomeViewHolder>(diffCallback) {

    private lateinit var callback : IHomeAdapter

    fun setCallback(callback: IHomeAdapter){
        this.callback = callback
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Game>() {
            override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(parent)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindData(getItem(position))
        holder.itemView.setOnClickListener {
            callback.onItemHomeClicked()
        }
    }

}