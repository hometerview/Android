package com.ftw.hometerview.ui.main.favorite.favoritelist

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ftw.hometerview.R
import com.ftw.hometerview.adapter.DataBindingRecyclerAdapter
import com.ftw.hometerview.adapter.SpacingItemDecoration
import com.ftw.hometerview.databinding.FragmentFavoriteListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteListFragment : Fragment() {

    companion object {
        fun newInstance(favorite: String): FavoriteListFragment {
            return FavoriteListFragment().apply {
                arguments = bundleOf(
                    FavoriteListFragment::class.java.simpleName to Argument(favorite)
                )
            }
        }
    }

    private var _binding: FragmentFavoriteListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: FavoriteListViewModel

    private val adapter = DataBindingRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate<FragmentFavoriteListBinding?>(
            inflater,
            R.layout.fragment_favorite_list,
            container,
            false
        ).apply {
            viewModel = this@FavoriteListFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val argument = arguments?.getParcelable<Argument>(this.javaClass.simpleName) ?: return
        initList()
        observe(argument.favorite)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initList() {
        binding.list.adapter = adapter
        context?.let { binding.list.addItemDecoration(SpacingItemDecoration(it)) }
    }

    private fun observe(favorite: String) {
        if (favorite == "저장한 리뷰") {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.savedReviewsItems.collect {
                        adapter.submitList(it)
                    }
                }
            }
        } else if (favorite == "저장한 건물") {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.savedBuildingsItems.collect {
                        adapter.submitList(it)
                    }
                }
            }
        }
    }

    @Parcelize
    private data class Argument(
        val favorite: String
    ) : Parcelable

}
