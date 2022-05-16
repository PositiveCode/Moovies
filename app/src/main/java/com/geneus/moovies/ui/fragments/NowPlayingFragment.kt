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
import com.geneus.moovies.data.api.model.Movie
import com.geneus.moovies.ui.MovieDetailsActivity
import com.geneus.moovies.ui.MovieDetailsActivity.Companion.INTENT_KEY_MOVIE_DETAIL_MOVIE_ID
import com.geneus.moovies.ui.adapter.MovieListAdapter
import com.geneus.moovies.ui.viewmodel.NowPlayingViewModel
import com.geneus.moovies.utils.Status
import com.geneus.moovies.utils.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class NowPlayingFragment : Fragment() {
    private val vm: NowPlayingViewModel by viewModel()
    private lateinit var rvMoviesList: RecyclerView

    private var adapter: MovieListAdapter? = null
    private var cachedMovieList: ArrayList<Movie> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_now_playing, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)

        vm.getNowPlayingMovies()
        setupObserver()
    }

    private fun setupObserver() {
        vm.movieList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { moviesList ->
                        if (cachedMovieList.isEmpty()) {
                            cachedMovieList = moviesList
                            setRecyclerView(cachedMovieList)
                        } else {
                            /**
                             * update the cached list with the new movies list
                             * then update the rv adapter.
                             * */
                            cachedMovieList.addAll(moviesList)
                            adapter?.setItems(cachedMovieList)
                            adapter?.notifyDataSetChanged()
                        }
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

    private fun openMovieDetail(movie: Movie) {
        context?.startActivity<MovieDetailsActivity> {
            putExtra(INTENT_KEY_MOVIE_DETAIL_MOVIE_ID, movie.id?.toInt()?: 0)
        }
    }

    private fun setRecyclerView(moviesList: ArrayList<Movie>) {
        rvMoviesList.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        adapter =
            MovieListAdapter(
                requireContext(),
                moviesList,
                this::openMovieDetail
            )

        rvMoviesList.adapter = adapter

        rvMoviesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            /**
             * Detects when the recyclerview scrolled to end.
             * */
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    vm.getNowPlayingMovies()
                }
            }
        })

    }
}