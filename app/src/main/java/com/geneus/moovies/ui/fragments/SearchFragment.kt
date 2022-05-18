package com.geneus.moovies.ui.fragments

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geneus.moovies.R
import com.geneus.moovies.data.api.model.Movie
import com.geneus.moovies.ui.MovieDetailsActivity
import com.geneus.moovies.ui.adapter.MovieListAdapter
import com.geneus.moovies.ui.viewmodel.SearchViewModel
import com.geneus.moovies.utils.Status
import com.geneus.moovies.utils.hideKeyboard
import com.geneus.moovies.utils.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {
    private val vm: SearchViewModel by viewModel()
    private var cachedMovieList: ArrayList<Movie> = arrayListOf()
    private var adapter: MovieListAdapter? = null

    private lateinit var rvMoviesList: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var etSearch: EditText
    private lateinit var ivSearch: ImageView
    private lateinit var ivEmpty: ImageView
    private lateinit var tvEmpty: TextView

    private var searchedQuery = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupObserver()
    }

    private fun setupViews(view: View) {
        rvMoviesList = view.findViewById(R.id.rvMoviesList)
        progressBar = view.findViewById(R.id.progressBar)
        etSearch = view.findViewById(R.id.etSearch)
        ivSearch = view.findViewById(R.id.ivSearch)
        ivEmpty = view.findViewById(R.id.ivEmpty)
        tvEmpty = view.findViewById(R.id.tvEmpty)

        etSearch.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchedQuery = etSearch.text.toString()
                    vm.getSearchedMovie(searchedQuery)
                    return true
                }
                return false
            }
        })
    }

    private fun setupObserver() {
        vm.movieList.observe(viewLifecycleOwner) {
            hideKeyboard()

            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { moviesList ->
                        progressBar.visibility = View.GONE

                        if (moviesList.isEmpty()) {
                            tvEmpty.visibility = View.VISIBLE
                            ivEmpty.visibility = View.VISIBLE
                            rvMoviesList.visibility = View.GONE
                        } else {
                            tvEmpty.visibility = View.GONE
                            ivEmpty.visibility = View.GONE
                            rvMoviesList.visibility = View.VISIBLE
                        }

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
                    progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE

                    tvEmpty.visibility = View.VISIBLE
                    ivEmpty.visibility = View.VISIBLE
                    rvMoviesList.visibility = View.GONE

                    Toast.makeText(
                        context,
                        "${it.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun setRecyclerView(moviesList: ArrayList<Movie>) {
        rvMoviesList.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        adapter =
            MovieListAdapter(
                requireContext(),
                moviesList,
                vm.getLocalGenreList(),
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
                    vm.getSearchedMovie(searchedQuery)
                }
            }
        })

    }

    private fun openMovieDetail(movie: Movie) {
        context?.startActivity<MovieDetailsActivity> {
            putExtra(MovieDetailsActivity.INTENT_KEY_MOVIE_DETAIL_MOVIE_ID, movie.id?.toInt() ?: 0)
        }
    }
}