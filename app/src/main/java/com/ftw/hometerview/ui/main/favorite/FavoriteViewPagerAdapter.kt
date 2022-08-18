package com.ftw.hometerview.ui.main.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ftw.hometerview.ui.main.favorite.favoritelist.FavoriteBuildingsFragment
import com.ftw.hometerview.ui.main.favorite.favoritelist.FavoriteReviewsFragment

class FavoriteViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragments: ArrayList<Fragment> = arrayListOf()

    init {
        fragments.add(FavoriteReviewsFragment.newInstance())
        fragments.add(FavoriteBuildingsFragment.newInstance())
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}
