package com.ftw.hometerview.ui.main.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ftw.hometerview.ui.main.favorite.favoritelist.FavoriteListFragment
import com.ftw.hometerview.ui.main.home.review.LocationReviewListFragment

class FavoriteViewPagerAdapter(fragmentActivity: FragmentActivity, favorite: List<String>) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragments: ArrayList<Fragment> = arrayListOf()

    init {
        favorite.forEach {
            fragments.add(FavoriteListFragment.newInstance(it))
        }
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}
