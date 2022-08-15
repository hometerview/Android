package com.ftw.hometerview.ui.review.second

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.FragmentCreateReviewSecondStepBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@AndroidEntryPoint
class CreateReviewSecondStepFragment : Fragment() {
    companion object {
        private const val ARGUMENT_KEY = "CREATE_REVIEW_SECOND_STEP_ARGUMENT_KEY"
        fun newInstance(address: String): CreateReviewSecondStepFragment {
            return CreateReviewSecondStepFragment().apply {
                arguments = bundleOf(ARGUMENT_KEY to Argument(address))
            }
        }
    }

    private var _binding: FragmentCreateReviewSecondStepBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: CreateReviewSecondStepViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate<FragmentCreateReviewSecondStepBinding?>(
            inflater,
            R.layout.fragment_create_review_second_step,
            container,
            false
        ).apply {
            this.viewModel = this@CreateReviewSecondStepFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Argument>(ARGUMENT_KEY)?.also { argument ->
            viewModel.setAddress(argument.address)
        } ?: let {
            activity?.onBackPressed()
            return
        }

        observe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect { event ->
                    when (event) {
                        CreateReviewSecondStepViewModel.Event.Nothing -> {}
                        CreateReviewSecondStepViewModel.Event.OnClickResidentialFloor -> showSelectResidentialFloorBottomSheet()
                    }
                }
            }
        }
    }

    private fun showSelectResidentialFloorBottomSheet() {
        // TODO 거주층 선택 반팝업 노출
        Log.d("SecondStep", "showSelectResidentialFloorBottomSheet: ")
    }

    @Parcelize
    data class Argument(val address: String) : Parcelable
}
