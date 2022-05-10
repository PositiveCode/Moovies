package com.geneus.moovies.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geneus.moovies.R
import com.geneus.moovies.view.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val vm: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm.getNowPlayingMovies()
    }
}