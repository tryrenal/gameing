package com.redveloper.creator.ui

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.redveloper.creator.core.domain.model.Creator

class CreatorAdapter() : PagingDataAdapter<Creator, CreatorViewHolder>(diffCallback){

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatorViewHolder {
        return CreatorViewHolder(parent)
    }

    override fun onBindViewHolder(holder: CreatorViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

}