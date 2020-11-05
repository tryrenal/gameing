package com.redveloper.creator.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.redveloper.creator.R
import com.redveloper.creator.core.domain.model.Creator
import kotlinx.android.synthetic.main.layout_item_creator_odd.view.*

class CreatorViewHolderOdd(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.layout_item_creator_odd, parent, false)
) {
    fun bindDataOdd(data: Creator?) {
        with(itemView) {
            Glide.with(this)
                .load(data?.image)
                .into(img_item_creator_odd)

            tv_name_item_creator_odd.text = data?.name
            tv_games_count_item_creator_odd.text = data?.gamesCount.toString()
        }
    }
}