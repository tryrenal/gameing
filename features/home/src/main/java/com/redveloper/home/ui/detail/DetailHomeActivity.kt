package com.redveloper.home.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.redveloper.home.R
import com.redveloper.home.utils.idGame
import kotlinx.android.synthetic.main.activity_detail_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailHomeActivity : AppCompatActivity() {

    val detailHomeViewModel : DetailHomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_home)

        val bundle = intent.extras
        if (bundle != null){
            val id = bundle.getInt(idGame)
            detailHomeViewModel.getDetailGame(id).observe(this, { data ->
                dummy_text.text = data.name
            })
        }
    }
}