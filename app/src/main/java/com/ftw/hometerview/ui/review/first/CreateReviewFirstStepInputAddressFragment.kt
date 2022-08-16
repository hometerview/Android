package com.ftw.hometerview.ui.review.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ftw.hometerview.R
import com.ftw.hometerview.adapter.DataBindingRecyclerAdapter
import com.ftw.hometerview.adapter.DividerItemDecoration
import com.ftw.hometerview.databinding.FragmentCreateReviewFirstStepInputAddressBinding
import com.ftw.hometerview.extension.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateReviewFirstStepInputAddressFragment : Fragment() {
    companion object {
        fun newInstance(): CreateReviewFirstStepInputAddressFragment =
            CreateReviewFirstStepInputAddressFragment()
    }

    interface Listener {
        fun onClickAddressFromFirstStep(address: String)
    }

    private var _binding: FragmentCreateReviewFirstStepInputAddressBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: CreateReviewFirstStepInputAddressViewModel

    private val addressAdapter = DataBindingRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate<FragmentCreateReviewFirstStepInputAddressBinding?>(
            inflater,
            R.layout.fragment_create_review_first_step_input_address,
            container,
            false
        ).apply {
            this.viewModel = this@CreateReviewFirstStepInputAddressFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setList()
        observe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setList() {
        binding.addressRecyclerView.adapter = addressAdapter
        binding.addressRecyclerView.addItemDecoration(DividerItemDecoration(requireContext()))
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.addressItems.collect { list ->
                    addressAdapter.submitList(list)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect { event ->
                    when (event) {
                        is CreateReviewFirstStepInputAddressViewModel.Event.OnClickAddress -> {
                            hideKeyboard()
                            (activity as? Listener)?.onClickAddressFromFirstStep(event.address)
                        }
                        else -> {
                            // Do Nothing
                        }
                    }
                }
            }
        }
    }
}
