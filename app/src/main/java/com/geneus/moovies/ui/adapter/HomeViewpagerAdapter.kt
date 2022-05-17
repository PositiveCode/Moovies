package com.geneus.moovies.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.geneus.moovies.R
import com.geneus.moovies.data.repo.MovieRepo
import com.geneus.moovies.ui.fragments.MovieListFragment

class HomeViewpagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    val fragmentProperties = arrayOf(
        TabProperty(MovieListFragment.newInstance(MovieRepo.Category.NOW_PLAYING), "Now Playing", R.drawable.ic_now_playing_movies),
        TabProperty(MovieListFragment.newInstance(MovieRepo.Category.POPULAR), "Popular", R.drawable.ic_popular_movies),
        TabProperty(MovieListFragment.newInstance(MovieRepo.Category.TOP_RATED), "Top Rated", R.drawable.ic_top_rated_movies),
        TabProperty(MovieListFragment.newInstance(MovieRepo.Category.UPCOMING), "Upcoming", R.drawable.ic_upcoming_movies)
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