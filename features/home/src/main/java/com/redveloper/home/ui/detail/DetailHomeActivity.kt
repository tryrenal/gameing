package com.redveloper.home.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.redveloper.core.vo.Resource
import com.redveloper.home.R
import com.redveloper.home.core.domain.model.Game
import com.redveloper.home.utils.idGame
import kotlinx.android.synthetic.main.activity_detail_home.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class DetailHomeActivity : AppCompatActivity() {

    val detailHomeViewModel : DetailHomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_home)

        val bundle = intent.extras
        if (bundle != null){
            val id = bundle.getInt(idGame)
            getDataDetail(id)
        }
    }

    private fun getDataDetail(id: Int){
        detailHomeViewModel.getDetailGame(id).observe(this, { data ->
            if (data != null){
                when(data) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        data.data?.let { setDataDetail(it) }
                    }
                    is Resource.Error -> {
                        Timber.e(data.message)
                    }
                }
            }
        })
    }

    private fun setDataDetail(data: Game){
        tv_title_detail_home.text = data.name
        tv_released_date_detail_home.text = data.released
        tv_rating_detail_home.text = data.rating.toString()
        tv_slug_detail_home.text = data.slug
        Glide.with(this)
            .load(data.backgroundImage)
            .into(img_detail_home)
        tv_descrtiption_detail_home.text = data.desc
    }
}