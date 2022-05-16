package com.geneus.moovies.ui

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.geneus.moovies.R

class MovieDetailsActivity : AppCompatActivity() {
    private val icBack by lazy { findViewById<ImageButton>(R.id.icBack) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        icBack.setOnClickListener {
            onBackPressed()
        }
    }
}