package com.geneus.moovies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geneus.moovies.R
import com.geneus.moovies.ui.fragments.FavouritesFragment
import com.geneus.moovies.ui.fragments.HomeFragment
import com.geneus.moovies.ui.fragments.SearchFragment
import com.geneus.moovies.ui.viewmodel.MainViewModel
import me.ibrahimsn.lib.SmoothBottomBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val vm: MainViewModel by viewModel()
    private val bottomNavBar by lazy { findViewById<SmoothBottomBar>(R.id.bottomBar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm.getGenre()
        /**
         * set default fragment
         * */
        setFragment(FRAGMENT_HOME)

        bottomNavBar.onItemSelected = {
            when (it) {
                0 -> {
                    setFragment(FRAGMENT_HOME)
                }
                1 -> {
                    setFragment(FRAGMENT_SEARCH)
                }
                2 -> {
                    setFragment(FRAGMENT_FAVOURITES)
                }
            }
        }
    }

    private fun setFragment(fragmentToShowTag: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragmentToShow = supportFragmentManager.findFragmentByTag(fragmentToShowTag)

        /**
         * show the intended fragment
         * */
        when (fragmentToShow != null) {
            true -> {
                fragmentTransaction.show(fragmentToShow)
            }
            else -> {
                /**
                 * add the fragment to the manager if it doesnt not exist.
                 * */
                when (fragmentToShowTag) {
                    FRAGMENT_HOME -> fragmentTransaction.add(
                        R.id.container, HomeFragment(), FRAGMENT_HOME
                    )
                    FRAGMENT_FAVOURITES -> fragmentTransaction.add(
                        R.id.container, FavouritesFragment(), FRAGMENT_FAVOURITES
                    )
                    FRAGMENT_SEARCH -> {
                        fragmentTransaction.add(
                            R.id.container,
                            SearchFragment(),
                            FRAGMENT_SEARCH
                        )
                    }
                }
            }
        }

        /**
         * hide all other fragments - except the fragment to show.
         * */
        for (fragment in fragmentManager.fragments) {
            if (fragment != fragmentToShow)
                fragmentTransaction.hide(fragment)
        }

        fragmentTransaction.commit()
    }

    companion object {
        const val FRAGMENT_HOME = "FRAGMENT_HOME"
        const val FRAGMENT_SEARCH = "FRAGMENT_SEARCH"
        const val FRAGMENT_FAVOURITES = "FRAGMENT_FAVOURITES"
    }
}