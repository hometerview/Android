package com.ftw.hometerview.ui.main.home

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.FragmentHomeBinding
import com.ftw.hometerview.ui.main.HomeTabItemView
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.abs

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        private const val TOOLBAR_LAYOUT_EXPANDING_OFFSET = 100
        fun newInstance() = HomeFragment()
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: HomeViewModel

    private lateinit var adapter: HomeViewPagerAdapter
    private var toolbarLayoutState: ToolbarLayoutState = ToolbarLayoutState.EXPANDING

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate<FragmentHomeBinding?>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        ).apply {
            viewModel = this@HomeFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setOnOffsetChangedListener()
        startInducementBanner()
        observe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.reviews.collect { reviews ->
                    val locations = reviews.map { it.location }
                    binding.viewPager.adapter =
                        HomeViewPagerAdapter(activity ?: return@collect, locations)
                    TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                        tab.customView = HomeTabItemView(requireContext()).apply {
                            argument = HomeTabItemView.Argument(
                                title = locations[position],
                                showBadge = position == 0
                            )
                        }
                    }.attach()
                }
            }
        }
    }

    private fun setOnOffsetChangedListener() {
        binding.appBarLayout.addOnOffsetChangedListener { _, verticalOffset ->
            val totalScrollRange = binding.appBarLayout.totalScrollRange
            val absVerticalOffset = abs(verticalOffset)

            if (absVerticalOffset >= totalScrollRange - TOOLBAR_LAYOUT_EXPANDING_OFFSET && toolbarLayoutState != ToolbarLayoutState.COLLAPSED) { // collapsed
                toolbarLayoutState = ToolbarLayoutState.COLLAPSED
                binding.tabLayout.background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.bg_home_tab_collapsed_item_background
                )
                binding.tabLayout.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    height = resources.getDimensionPixelSize(R.dimen.home_tab_layout_collapsed_contents_height)
                }

                for (i in 0 until binding.tabLayout.tabCount) {
                    (binding.tabLayout.getTabAt(i)?.customView as? HomeTabItemView)?.updateCollapsedBackground()
                }
            } else if (absVerticalOffset < totalScrollRange - (TOOLBAR_LAYOUT_EXPANDING_OFFSET * 2) && toolbarLayoutState != ToolbarLayoutState.EXPANDING) { // expanding
                toolbarLayoutState = ToolbarLayoutState.EXPANDING
                binding.tabLayout.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        android.R.color.transparent
                    )
                )
                binding.tabLayout.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    height = resources.getDimensionPixelSize(R.dimen.home_tab_layout_expanding_contents_height)
                }

                for (i in 0 until binding.tabLayout.tabCount) {
                    (binding.tabLayout.getTabAt(i)?.customView as? HomeTabItemView)?.updateExpandedBackground()
                }
            }
        }
    }

    private fun startInducementBanner() {
        binding.inducementLayout.isVisible = false
        ValueAnimator.ofInt(
            0,
            resources.getDimensionPixelSize(R.dimen.home_inducement_banner_height)
        ).apply {
            addUpdateListener { valueAnimator ->
                val value = valueAnimator.animatedValue as? Int ?: 0
                binding.inducementEmptyLayout.updateLayoutParams<ViewGroup.LayoutParams> {
                    height = value
                }
            }

            doOnEnd {
                binding.inducementLayout.isVisible = true
                binding.inducementLayout.startAnimation(
                    AlphaAnimation(0f, 1f).apply {
                        duration = 1000
                    }
                )
            }
            duration = 2000
        }.start()
    }

    private enum class ToolbarLayoutState {
        COLLAPSED, EXPANDING
    }
}
