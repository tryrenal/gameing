package com.redveloper.creator.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.redveloper.creator.R
import com.redveloper.creator.core.domain.model.Creator
import kotlinx.android.synthetic.main.layout_item_creator_even.view.*

class CreatorViewHolderEven(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.layout_item_creator_even, parent, false)
) {

    fun bindDataEven(data: Creator?){
        with(itemView){
            Glide.with(this)
                .load(data?.image)
                .into(img_item_creator_even)

            tv_name_item_creator_even.text = data?.name
            tv_games_count_item_creator_even.text = data?.gamesCount.toString()
        }
    }
}