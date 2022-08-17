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
import com.ftw.hometerview.databinding.FragmentCreateReviewSecondStepBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date
import javax.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateReviewSecondStepReviewFragment : Fragment() {
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

    private var _binding: FragmentCreateReviewSecondStepBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: CreateReviewSecondStepReviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate<FragmentCreateReviewSecondStepBinding?>(inflater, R.layout.fragment_create_review_second_step_review, container, false).apply {
            this.viewModel = this@CreateReviewSecondStepReviewFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        lifecycleScope.launch { repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.event.collect { event ->
                when (event) {
                    CreateReviewSecondStepReviewViewModel.State.None -> {}
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
}
