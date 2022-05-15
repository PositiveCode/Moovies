package com.geneus.moovies.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.geneus.moovies.R
import com.geneus.moovies.ui.adapter.HomeViewpagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {
    private lateinit var viewpager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager(view)
        setupTablayout(view)
    }

    private fun setupViewPager(view: View) {
        val pagerAdapter = HomeViewpagerAdapter(this)
        viewpager = view.findViewById(R.id.viewpager)
        viewpager.adapter = pagerAdapter
    }

    private fun setupTablayout(view: View) {
        val tabLayout: TabLayout = view.findViewById(R.id.tabs)
        TabLayoutMediator(
            tabLayout, viewpager
        ) { tab, position ->
            val tabProperty =
                (viewpager.adapter as HomeViewpagerAdapter).fragmentProperties[position]

            tab.customView = createCustomTabView(tabProperty)

        }.attach()
    }


    private fun createCustomTabView(tabProperty: HomeViewpagerAdapter.TabProperty): LinearLayout {
        /**
         * setup a custom tab view and add it into tabLayout*/
        val tabLinearLayout =
            LayoutInflater.from(context).inflate(R.layout.tab_custom, null) as LinearLayout

        val tabTitle = tabLinearLayout.findViewById<View>(R.id.tvTabTitle) as TextView
        val tabIcon = tabLinearLayout.findViewById<View>(R.id.ivTabIcon) as ImageView
        tabTitle.text = tabProperty.title
        tabIcon.setImageResource(tabProperty.iconSrc)

        return tabLinearLayout
    }
}