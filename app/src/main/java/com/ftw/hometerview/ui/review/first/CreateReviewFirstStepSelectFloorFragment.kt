package com.ftw.hometerview.ui.review.first

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ftw.domain.entity.Floor
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.FragmentCreateReviewFirstStepSelectFloorBinding
import com.ftw.hometerview.ui.review.residentialfloor.SelectResidentialFloorBottomSheet
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize

@AndroidEntryPoint
class CreateReviewFirstStepSelectFloorFragment : Fragment(), SelectResidentialFloorBottomSheet.Listener {
    companion object {
        private const val ARGUMENT_KEY = "CREATE_REVIEW_SECOND_STEP_ARGUMENT_KEY"
        fun newInstance(address: String): CreateReviewFirstStepSelectFloorFragment {
            return CreateReviewFirstStepSelectFloorFragment().apply {
                arguments = bundleOf(ARGUMENT_KEY to Argument(address))
            }
        }
    }

    interface Listener {
        fun onClickNextFromFirstStepResidentialFloor(address: String, floor: String)
    }

    private var _binding: FragmentCreateReviewFirstStepSelectFloorBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: CreateReviewFirstStepSelectFloorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate<FragmentCreateReviewFirstStepSelectFloorBinding?>(
            inflater,
            R.layout.fragment_create_review_first_step_select_floor,
            container,
            false
        ).apply {
            this.viewModel = this@CreateReviewFirstStepSelectFloorFragment.viewModel
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
        showSelectResidentialFloorBottomSheet()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickFromSelectResidentialFloorBottomSheet(floor: Floor) {
        viewModel.setResidentialFloor(getFloorText(floor))
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect { event ->
                    when (event) {
                        CreateReviewFirstStepSelectFloorViewModel.Event.Nothing -> {}
                        CreateReviewFirstStepSelectFloorViewModel.Event.OnClickResidentialFloor -> showSelectResidentialFloorBottomSheet()
                        is CreateReviewFirstStepSelectFloorViewModel.Event.OnClickNext -> {
                            (activity as? Listener)?.onClickNextFromFirstStepResidentialFloor(
                                event.address,
                                event.floor
                            )
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.residentialFloor.collect {
                    binding.residentialFloorEditText.setText(it)
                }
            }
        }
    }

    private fun showSelectResidentialFloorBottomSheet() {
        SelectResidentialFloorBottomSheet.newInstance()
            .show(childFragmentManager, SelectResidentialFloorBottomSheet::class.simpleName)
    }

    private fun getFloorText(floor: Floor): String {
        return when (floor) {
            Floor.HIGH -> getString(R.string.residential_floor_high)
            Floor.MIDDLE -> getString(R.string.residential_floor_middle)
            Floor.LOW -> getString(R.string.residential_floor_low)
            Floor.PRIVATE -> getString(R.string.residential_floor_private)
        }
    }

    @Parcelize
    data class Argument(val address: String) : Parcelable
}
