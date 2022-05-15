package com.geneus.moovies.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.geneus.moovies.R
import com.geneus.moovies.ui.fragments.NowPlayingFragment
import com.geneus.moovies.ui.fragments.PopularFragment
import com.geneus.moovies.ui.fragments.TopRatedFragment
import com.geneus.moovies.ui.fragments.UpcomingFragment

class HomeViewpagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    val fragmentProperties = arrayOf(
        TabProperty(NowPlayingFragment(), "Now Playing", R.drawable.ic_now_playing_movies),
        TabProperty(PopularFragment(), "Popular", R.drawable.ic_popular_movies),
        TabProperty(TopRatedFragment(), "Top Rated", R.drawable.ic_top_rated_movies),
        TabProperty(UpcomingFragment(), "Upcoming", R.drawable.ic_upcoming_movies)
    )

    override fun getItemCount(): Int {
        return fragmentProperties.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentProperties[position].fragment
    }

    data class TabProperty(
        val fragment: Fragment,
        val title: String,
        val iconSrc: Int
    )
}