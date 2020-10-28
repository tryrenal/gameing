package com.redveloper.creator.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redveloper.creator.core.domain.model.Creator

class CreatorAdapter() : RecyclerView.Adapter<CreatorViewHolder>(){

    private var items: ArrayList<Creator> = ArrayList()

    constructor(data: List<Creator>) : this(){
        this.items = data as ArrayList<Creator>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatorViewHolder {
        return CreatorViewHolder(parent)
    }

    override fun onBindViewHolder(holder: CreatorViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}