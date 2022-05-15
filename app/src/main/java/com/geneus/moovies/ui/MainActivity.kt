package com.geneus.moovies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geneus.moovies.R
import com.geneus.moovies.ui.fragments.NowPlayingFragment
import com.geneus.moovies.ui.fragments.PopularFragment
import com.geneus.moovies.ui.fragments.TopRatedFragment
import com.geneus.moovies.ui.fragments.UpcomingFragment
import me.ibrahimsn.lib.SmoothBottomBar

class MainActivity : AppCompatActivity() {
    private val bottomNavBar by lazy { findViewById<SmoothBottomBar>(R.id.bottomBar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * set default fragment
         * */
        setFragment(FRAGMENT_NOW_PLAYING_TAG)

        bottomNavBar.onItemSelected = {
            when (it) {
                0 -> {
                    setFragment(FRAGMENT_NOW_PLAYING_TAG)
                }
                1 -> {
                    setFragment(FRAGMENT_POPULAR_TAG)
                }
                2 -> {
                    setFragment(FRAGMENT_TOP_RATED_TAG)
                }
                3 -> {
                    setFragment(FRAGMENT_UPCOMING_TAG)
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
                    FRAGMENT_NOW_PLAYING_TAG -> fragmentTransaction.add(
                        R.id.container, NowPlayingFragment(), FRAGMENT_NOW_PLAYING_TAG
                    )
                    FRAGMENT_POPULAR_TAG -> fragmentTransaction.add(
                        R.id.container, PopularFragment(), FRAGMENT_POPULAR_TAG
                    )
                    FRAGMENT_TOP_RATED_TAG -> fragmentTransaction.add(
                        R.id.container, TopRatedFragment(), FRAGMENT_TOP_RATED_TAG
                    )
                    FRAGMENT_UPCOMING_TAG -> fragmentTransaction.add(
                        R.id.container, UpcomingFragment(), FRAGMENT_UPCOMING_TAG
                    )
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
        const val FRAGMENT_NOW_PLAYING_TAG = "FRAGMENT_NOW_PLAYING"
        const val FRAGMENT_POPULAR_TAG = "FRAGMENT_POPULAR"
        const val FRAGMENT_TOP_RATED_TAG = "FRAGMENT_TOP_RATED"
        const val FRAGMENT_UPCOMING_TAG = "FRAGMENT_UPCOMING"
    }
}