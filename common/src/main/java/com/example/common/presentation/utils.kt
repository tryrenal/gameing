package com.example.common.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.bumptech.glide.Glide

fun Context.toast(msg: String){
    Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
}

fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}

fun ImageView.loadImage(data: Any){
    Glide.with(context)
        .load(data)
        .into(this)
}

fun RecyclerView.scrollLeft(imageIlustration: ImageView, parent: ViewGroup){
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        @SuppressLint("RtlHardcoded")
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val transition = Slide(Gravity.LEFT)
            transition.addTarget(imageIlustration)
            TransitionManager.beginDelayedTransition(parent, transition)

            if (dx <= 0) {
                imageIlustration.show()
            } else {
                imageIlustration.gone()
            }
        }
    })
}