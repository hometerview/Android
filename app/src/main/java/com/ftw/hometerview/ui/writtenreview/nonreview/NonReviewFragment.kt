package com.ftw.hometerview.ui.writtenreview.nonreview

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
import com.ftw.hometerview.databinding.FragmentNonReviewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NonReviewFragment : Fragment() {

    companion object {
        fun newInstance(): NonReviewFragment {
            return NonReviewFragment()
        }
    }

    private var _binding: FragmentNonReviewBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: NonReviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate<FragmentNonReviewBinding?>(
            inflater,
            R.layout.fragment_non_review,
            container,
            false
        ).apply {
            viewModel = this@NonReviewFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeEvent()
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
                        NonReviewViewModel.Event.None -> {}
                        NonReviewViewModel.Event.onClickWriteReview -> {
                            // ㄹㅣ뷰 작성 화면으로 이동
                        }
                    }
                }
            }
        }
    }
}
