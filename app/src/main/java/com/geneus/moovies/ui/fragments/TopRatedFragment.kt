package com.geneus.moovies.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geneus.moovies.R
import com.geneus.moovies.ui.viewmodel.TopRatedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopRatedFragment  : Fragment() {
    private val vm: TopRatedViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_top_rated, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getTopRatedMovies()
    }
}