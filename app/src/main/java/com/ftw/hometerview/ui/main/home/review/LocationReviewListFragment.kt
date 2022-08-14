package com.ftw.hometerview.ui.main.home.review

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ftw.hometerview.R
import com.ftw.hometerview.adapter.DataBindingRecyclerAdapter
import com.ftw.hometerview.adapter.SpacingItemDecoration
import com.ftw.hometerview.databinding.FragmentLocationListBinding
import com.ftw.hometerview.ui.buildingreview.BuildingReviewActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize

@AndroidEntryPoint
class LocationReviewListFragment : Fragment() {

    companion object {
        fun newInstance(location: String): LocationReviewListFragment {
            return LocationReviewListFragment().apply {
                arguments = bundleOf(
                    LocationReviewListFragment::class.java.simpleName to Argument(location)
                )
            }
        }
    }

    private var _binding: FragmentLocationListBinding? = null
    private val binding get() = _binding!!

    private val adapter = DataBindingRecyclerAdapter()

    @Inject
    lateinit var viewModel: LocationReviewListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_location_list,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val argument = arguments?.getParcelable<Argument>(this.javaClass.simpleName) ?: return
        initList()
        observe()
        viewModel.setLocation(argument.location)
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
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collect { state ->
                    when (state) {
                        LocationReviewListViewModel.State.Loading -> Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                        is LocationReviewListViewModel.State.OnClickReview -> {
                            startActivity(BuildingReviewActivity.newIntent(requireContext(), state.buildingId))
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.reviews.collect {
                    adapter.submitList(it)
                }
            }
        }
    }

    @Parcelize
    private data class Argument(
        val location: String
    ) : Parcelable
}
