package com.geneus.moovies.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.geneus.moovies.R
import com.geneus.moovies.ui.viewmodel.NowPlayingViewModel
import com.geneus.moovies.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class NowPlayingFragment : Fragment() {
    private val vm: NowPlayingViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_now_playing, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getNowPlayingMovies()
        setupObserver()
    }

    private fun setupObserver() {
        vm.movieList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { moviesList ->
                        Toast.makeText(
                            context,
                            "SUCCESS",
                            Toast.LENGTH_LONG
                        ).show()
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
}