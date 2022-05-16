package com.geneus.moovies.ui

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.geneus.moovies.R
import com.geneus.moovies.ui.viewmodel.MovieDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsActivity : AppCompatActivity() {
    private val vm: MovieDetailsViewModel by viewModel()
    private val icBack by lazy { findViewById<ImageButton>(R.id.icBack) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        icBack.setOnClickListener {
            onBackPressed()
        }
    }

    companion object {
        const val INTENT_KEY_MOVIE_DETAIL_MOVIE_ID = "INTENT_KEY_MOVIE_DETAIL_MOVIE_ID"
    }
}