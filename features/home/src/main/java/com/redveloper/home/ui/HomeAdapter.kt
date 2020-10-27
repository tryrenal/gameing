package com.redveloper.home.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.redveloper.home.R
import com.redveloper.home.core.domain.model.Game
import kotlinx.android.synthetic.main.layout_item_home.view.*

class HomeAdapter() : RecyclerView.Adapter<HomeAdapter.ViewHolder>(){

    private var items: ArrayList<Game> = ArrayList()

    constructor(data: List<Game>) : this(){
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
            with(itemView){
                Glide.with(this)
                    .load(data.backgroundImage)
                    .into(img_item_home)
                tv_title_item_home.text = data.name
            }
        }
    }
}