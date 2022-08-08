package com.ftw.hometerview.ui.main.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ftw.hometerview.ui.main.home.review.LocationReviewListFragment

class HomeViewPagerAdapter(fragmentActivity: FragmentActivity, locations: List<String>) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragments: ArrayList<Fragment> = arrayListOf()

    init {
        locations.forEach {
            fragments.add(LocationReviewListFragment.newInstance(it))
        }
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}
