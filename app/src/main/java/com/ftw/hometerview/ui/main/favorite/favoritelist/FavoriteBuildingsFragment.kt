package com.ftw.hometerview.ui.main.favorite.favoritelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ftw.hometerview.R
import com.ftw.hometerview.adapter.DataBindingRecyclerAdapter
import com.ftw.hometerview.adapter.SpacingItemDecoration
import com.ftw.hometerview.databinding.FragmentFavoriteBuildingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteBuildingsFragment : Fragment() {

    companion object {
        fun newInstance(): FavoriteBuildingsFragment {
            return FavoriteBuildingsFragment()
        }
    }

    private var _binding: FragmentFavoriteBuildingBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: FavoriteBuildingsViewModel

    private val adapter = DataBindingRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate<FragmentFavoriteBuildingBinding?>(
            inflater,
            R.layout.fragment_favorite_building,
            container,
            false
        ).apply {
            viewModel = this@FavoriteBuildingsFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initList()
        observe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initList() {
        binding.list.adapter = adapter
        context?.let { binding.list.addItemDecoration(SpacingItemDecoration(it)) }
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favoriteBuildingsItems.collect {
                    adapter.submitList(it)
                }
            }
        }
    }

}
