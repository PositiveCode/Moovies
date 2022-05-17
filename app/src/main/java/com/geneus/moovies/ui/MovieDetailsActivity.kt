package com.geneus.moovies.ui

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.geneus.moovies.R
import com.geneus.moovies.ui.viewmodel.MovieDetailsViewModel
import com.geneus.moovies.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsActivity : AppCompatActivity() {
    private val vm: MovieDetailsViewModel by viewModel()
    private val icBack by lazy { findViewById<ImageButton>(R.id.icBack) }

    private val ivMoviePoster by lazy { findViewById<ImageView>(R.id.ivMoviePoster) }
    private val ivAddFavourite by lazy { findViewById<ImageView>(R.id.ivAddFavourite) }

    private val tvTitle by lazy { findViewById<TextView>(R.id.tvTitle) }
    private val tvMovieTitle by lazy { findViewById<TextView>(R.id.tvMovieTitle) }
    private val tvTagline by lazy { findViewById<TextView>(R.id.tvTagline) }
    private val tvMovieGenre by lazy { findViewById<TextView>(R.id.tvMovieGenre) }
    private val tvMovieReleaseDate by lazy { findViewById<TextView>(R.id.tvMovieReleaseDate) }
    private val tvOverview by lazy { findViewById<TextView>(R.id.tvOverview) }
    private val tvRating by lazy { findViewById<TextView>(R.id.tvRating) }
    private val progressBar by lazy { findViewById<ProgressBar>(R.id.progressBar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        icBack.setOnClickListener {
            onBackPressed()
        }

        val movieId = intent.getIntExtra(INTENT_KEY_MOVIE_DETAIL_MOVIE_ID, 0)
        vm.getMovieById(movieId)
        setupObserver()
    }

    private fun setupObserver() {
        vm.movieDetail.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { movieDetail ->
                        setMovieBackdrop(movieDetail.backdropPath)
                        setMovieTitle(movieDetail.title)
                        setMovieTagline(movieDetail.tagline)
                        setMovieReleaseDate(movieDetail.releaseDate)
                        setMovieOverview(movieDetail.overview)
                        setMovieRating(movieDetail.voteAverage.toString())
                        setLoadEndViewVisibility()

                        ivAddFavourite.setOnClickListener {
                            Toast.makeText(this, "Movie added to favourite.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                Status.LOADING -> {
                    setLoadingViewVisibility()
                }
                Status.ERROR -> {
                    Toast.makeText(
                        this,
                        "${it.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun setMovieBackdrop(backdropPath: String?) {
        if (backdropPath.isNullOrEmpty()) return

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/original${backdropPath}")
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_img_default_placeholder)
            .into(ivMoviePoster)
    }

    private fun setMovieTitle(movieTitle: String?) {
        if (movieTitle.isNullOrEmpty()) return

        tvTitle.text = movieTitle
        tvMovieTitle.text = movieTitle
    }

    private fun setMovieTagline(movieTagline: String?) {
        if (movieTagline.isNullOrEmpty()) return

        tvTagline.text = movieTagline
    }

    private fun setMovieReleaseDate(releaseDate: String?) {
        if (releaseDate.isNullOrEmpty()) return

        tvMovieReleaseDate.text = releaseDate
    }

    private fun setMovieOverview(movieOverview: String?) {
        if (movieOverview.isNullOrEmpty()) return

        tvOverview.text = movieOverview
    }

    private fun setMovieRating(movieRating: String?) {
        if (movieRating.isNullOrEmpty()) return

        tvRating.text = movieRating
    }

    private fun setLoadingViewVisibility() {
        progressBar.visibility = View.VISIBLE
        tvMovieTitle.visibility = View.GONE
        tvTagline.visibility = View.GONE
        tvMovieGenre.visibility = View.GONE
        tvMovieReleaseDate.visibility = View.GONE
        tvOverview.visibility = View.GONE
    }

    private fun setLoadEndViewVisibility() {
        progressBar.visibility = View.GONE
        tvMovieTitle.visibility = View.VISIBLE
        tvTagline.visibility = View.VISIBLE
        tvMovieGenre.visibility = View.VISIBLE
        tvMovieReleaseDate.visibility = View.VISIBLE
        tvOverview.visibility = View.VISIBLE
    }

    companion object {
        const val INTENT_KEY_MOVIE_DETAIL_MOVIE_ID = "INTENT_KEY_MOVIE_DETAIL_MOVIE_ID"
    }
}