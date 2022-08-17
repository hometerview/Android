package com.ftw.hometerview.ui.main.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.FragmentFavoriteBinding
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate<FragmentFavoriteBinding?>(
            inflater,
            R.layout.fragment_favorite,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTabBar()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initTabBar() {

        val favorite = listOf(getString(R.string.favorite_review_text), getString(R.string.favorite_building_text))
        binding.viewPager.adapter = FavoriteViewPagerAdapter(requireActivity())

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.customView = FavoriteTabItemView(requireContext()).apply {
                argument = FavoriteTabItemView.Argument(
                    title = favorite[position]
                )
            }
        }.attach()

        for (i in 0 until binding.tabLayout.tabCount) {
            (binding.tabLayout.getTabAt(i)?.customView as? FavoriteTabItemView)?.updateDefauotBackground()
        }
    }

}
