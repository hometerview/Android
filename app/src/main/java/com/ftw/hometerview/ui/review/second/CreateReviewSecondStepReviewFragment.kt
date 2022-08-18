package com.ftw.hometerview.ui.review.second

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
import com.ftw.hometerview.databinding.FragmentCreateReviewSecondStepReviewBinding
import com.ftw.hometerview.ui.bottomsheet.TextListBottomSheet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@AndroidEntryPoint
class CreateReviewSecondStepReviewFragment : Fragment(), TextListBottomSheet.Listener {
    companion object {
        fun newInstance() = CreateReviewSecondStepReviewFragment()
    }

    interface Listener {
        fun onClickNextFromSecondStep(
            rating: Int,
            leftAt: Date,
            advantage: String,
            disadvantage: String
        )
    }

    private var _binding: FragmentCreateReviewSecondStepReviewBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: CreateReviewSecondStepReviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate<FragmentCreateReviewSecondStepReviewBinding?>(
            inflater,
            R.layout.fragment_create_review_second_step_review,
            container,
            false
        ).apply {
            this.viewModel = this@CreateReviewSecondStepReviewFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvent()
        observeResidentialPeriod()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeEvent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect { event ->
                    when (event) {
                        CreateReviewSecondStepReviewViewModel.State.None -> {}
                        is CreateReviewSecondStepReviewViewModel.State.OnClickResidentialPeriod -> {
                            TextListBottomSheet.newInstance(getResidentialPeriods(event.periods))
                                .show(childFragmentManager, TextListBottomSheet::class.simpleName)
                        }
                        is CreateReviewSecondStepReviewViewModel.State.OnClickNext -> {
                            (activity as? Listener)?.onClickNextFromSecondStep(
                                event.rating,
                                event.leftAt,
                                event.advantage,
                                event.disadvantage
                            )
                        }
                    }
                }
            }
        }
    }

    private fun observeResidentialPeriod() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.residentialPeriod.collect { period ->
                    binding.residentialPeriodEditText.setText(period)
                }
            }
        }
    }

    private fun getResidentialPeriods(periods: List<Int>): List<String> {
        return periods.mapIndexed { index, period ->
            if (index != periods.lastIndex) {
                String.format(
                    getString(R.string.create_review_residential_period_until),
                    period
                )
            } else {
                String.format(
                    getString(R.string.create_review_residential_period_before),
                    period
                )
            }
        }
            .toMutableList()
            .apply {
                add(getString(R.string.create_review_residential_period_private))
            }
    }

    override fun onClickFromTextListBottomSheet(text: String) {
        viewModel.residentialPeriod.value = text
    }
}
