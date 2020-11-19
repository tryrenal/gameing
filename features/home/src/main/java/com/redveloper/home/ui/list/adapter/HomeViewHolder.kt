package com.redveloper.home.ui.list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.common.presentation.loadImage
import com.redveloper.home.R
import com.redveloper.home.core.domain.model.Game
import kotlinx.android.synthetic.main.layout_item_home.view.*

class HomeViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.layout_item_home, parent, false)
) {

    @SuppressLint("SetTextI18n")
    fun bindData(data: Game?) {
        with(itemView) {
            data?.backgroundImage?.let { img_item_home.loadImage(it) }
            tv_title_item_home.text = data?.name
            tv_released_date_item_home.text = "released : ${data?.released}"
            tv_rating_item_home.text = "rating : ${data?.rating}"
            rating_top.text = " / ${data?.ratingTop}"
        }
    }
}