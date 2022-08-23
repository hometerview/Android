package com.ftw.hometerview.ui.writtenreview.writtenreviewlist

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
import com.ftw.hometerview.databinding.FragmentFavoriteReviewBinding
import com.ftw.hometerview.databinding.FragmentWrittenReviewListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WrittenReviewListFragment : Fragment() {

    companion object {
        fun newInstance(): WrittenReviewListFragment {
            return WrittenReviewListFragment()
        }
    }

    private var _binding: FragmentWrittenReviewListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: WrittenReviewListViewModel

    private val adapter = DataBindingRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate<FragmentWrittenReviewListBinding?>(
            inflater,
            R.layout.fragment_written_review_list,
            container,
            false
        ).apply {
            viewModel = this@WrittenReviewListFragment.viewModel
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
                viewModel.writtenRevieewItems.collect {
                    adapter.submitList(it)
                }
            }
        }
    }

}
