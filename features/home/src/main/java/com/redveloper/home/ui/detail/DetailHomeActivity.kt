package com.redveloper.home.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.redveloper.home.R
import com.redveloper.home.utils.idGame
import timber.log.Timber

class DetailHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_home)

        val bundle = intent.extras
        if (bundle != null){
            val id = bundle.getInt(idGame)
        }
    }
}