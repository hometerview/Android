package com.ftw.hometerview.ui.review.third

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.FragmentCreateReviewThirdSetpSearchCompanyBinding
import com.ftw.hometerview.ui.model.ParcelableSearchResult
import com.ftw.hometerview.ui.searchcompany.SearchCompanyActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateReviewThirdStepSearchCompanyFragment : Fragment() {
    companion object {
        fun newInstance() = CreateReviewThirdStepSearchCompanyFragment()
    }

    interface Listener {
        fun onClickNextFromThirdStepSearchCompany(company: String)
    }

    private var _binding: FragmentCreateReviewThirdSetpSearchCompanyBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: CreateReviewThirdStepSearchCompanyViewModel

    private val searchCompanyLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode != Activity.RESULT_OK) return@registerForActivityResult
            result.data?.getParcelableExtra<ParcelableSearchResult>(
                SearchCompanyActivity.SEARCH_COMPANY_RESULT_KEY
            )?.run {
                viewModel.company.value = company
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate<FragmentCreateReviewThirdSetpSearchCompanyBinding?>(
            inflater,
            R.layout.fragment_create_review_third_setp_search_company,
            container,
            false
        ).apply {
            this.viewModel = this@CreateReviewThirdStepSearchCompanyFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvent()
        observeCompany()
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
                        CreateReviewThirdStepSearchCompanyViewModel.Event.None -> {}
                        CreateReviewThirdStepSearchCompanyViewModel.Event.OnClickSearchCompany -> showSearchCompanyActivity()
                        is CreateReviewThirdStepSearchCompanyViewModel.Event.OnClickNext -> {
                            (activity as? Listener)?.onClickNextFromThirdStepSearchCompany(event.company)
                        }
                    }
                }
            }
        }
    }

    private fun observeCompany() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.company.collect { company ->
                    binding.searchCompanyEditText.setText(company)
                }
            }
        }
    }

    private fun showSearchCompanyActivity() {
        searchCompanyLauncher.launch(SearchCompanyActivity.newIntent(requireContext()))
    }
}
