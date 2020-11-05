package com.redveloper.creator.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.redveloper.creator.core.domain.model.Creator

class CreatorAdapter : PagingDataAdapter<Creator, RecyclerView.ViewHolder>(diffCallback){

    companion object{
        private val diffCallback = object : DiffUtil.ItemCallback<Creator>(){
            override fun areItemsTheSame(oldItem: Creator, newItem: Creator): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Creator, newItem: Creator): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            0 -> CreatorViewHolderEven(parent)
            else -> CreatorViewHolderOdd(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType){
            0 -> {
                val viewHolder = holder as CreatorViewHolderEven
                viewHolder.bindDataEven(getItem(position))
            }
            1 -> {
                val viewHolder = holder as CreatorViewHolderOdd
                viewHolder.bindDataOdd(getItem(position))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position % 2
    }

}