package com.redveloper.creator.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.redveloper.creator.R
import com.redveloper.creator.core.domain.model.Creator
import kotlinx.android.synthetic.main.layout_item_creator.view.*

class CreatorViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.layout_item_creator, parent, false)
) {

    fun bindData(data: Creator?){
        with(itemView){
            Glide.with(this)
                .load(data?.image)
                .into(img_item_creator)

            tv_name_creator.text = data?.name
        }
    }
}