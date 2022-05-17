package com.geneus.moovies.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geneus.moovies.R
import com.geneus.moovies.data.db.model.MovieModel
import com.geneus.moovies.ui.MovieDetailsActivity
import com.geneus.moovies.ui.adapter.FavMovieListAdapter
import com.geneus.moovies.ui.viewmodel.FavouriteViewModel
import com.geneus.moovies.utils.Status
import com.geneus.moovies.utils.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouritesFragment : Fragment() {
    private val vm: FavouriteViewModel by viewModel()
    private lateinit var rvMoviesList: RecyclerView

    private var adapter: FavMovieListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_now_playing, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)

        vm.getAllFavMovies()
        setupObserver()
    }

    private fun setupObserver() {
        vm.movieDetail.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { moviesList ->
                        setRecyclerView(moviesList)
                    }
                }
                Status.LOADING -> {
                    Toast.makeText(
                        context,
                        "LOADING",
                        Toast.LENGTH_LONG
                    ).show()
                }
                Status.ERROR -> {
                    Toast.makeText(
                        context,
                        "${it.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun setupViews(view: View) {
        rvMoviesList = view.findViewById(R.id.rvMoviesList)
    }

    private fun openMovieDetail(movie: MovieModel) {
        context?.startActivity<MovieDetailsActivity> {
            putExtra(MovieDetailsActivity.INTENT_KEY_MOVIE_DETAIL_MOVIE_ID, movie.id?.toInt() ?: 0)
        }
    }

    private fun setRecyclerView(moviesList: List<MovieModel>) {
        rvMoviesList.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        adapter =
            FavMovieListAdapter(
                requireContext(),
                moviesList,
                this::openMovieDetail
            )

        rvMoviesList.adapter = adapter
    }
}