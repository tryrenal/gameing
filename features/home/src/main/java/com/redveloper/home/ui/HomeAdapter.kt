package com.redveloper.home.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redveloper.home.R
import com.redveloper.home.core.domain.model.Game

class HomeAdapter() : RecyclerView.Adapter<HomeAdapter.ViewHolder>(){

    private lateinit var items: ArrayList<Game>

    fun setData(data: List<Game>){
        this.items.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_item_home, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindData(data: Game){

        }
    }
}