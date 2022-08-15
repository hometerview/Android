package com.ftw.hometerview.ui.main.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.FragmentFavoriteBinding
import com.ftw.hometerview.ui.main.HomeTabItemView
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate<FragmentFavoriteBinding?>(
            inflater,
            R.layout.fragment_favorite,
            container,
            false
        ).apply {
            viewModel = this@FavoriteFragment.viewModel
        }
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

        val favorite = listOf("저장한 리뷰", "저장한 건물")
        binding.viewPager.adapter =
            FavoriteViewPagerAdapter(requireActivity(), favorite)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.customView = HomeTabItemView(requireContext()).apply {
                argument = HomeTabItemView.Argument(
                    title = favorite[position],
                    showBadge = position == 3
                )
            }
        }.attach()

        binding.tabLayout.background = ContextCompat.getDrawable(
            requireContext(),
            R.drawable.bg_home_tab_collapsed_item_background
        )

        for (i in 0 until binding.tabLayout.tabCount) {
            (binding.tabLayout.getTabAt(i)?.customView as? HomeTabItemView)?.updateCollapsedBackground()
        }
    }

}
