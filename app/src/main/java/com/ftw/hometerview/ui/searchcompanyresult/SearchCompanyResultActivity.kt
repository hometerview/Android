package com.ftw.hometerview.ui.searchcompanyresult

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ftw.domain.entity.SearchCompanyResult
import com.ftw.hometerview.databinding.ActivitySearchCompanyResultBinding
import com.ftw.hometerview.ui.model.toParcelable
import com.ftw.hometerview.ui.searchcompanynonresult.SearchCompanyNonResultActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize

@AndroidEntryPoint
class SearchCompanyResultActivity : AppCompatActivity() {

    companion object {
        const val SEARCH_COMPANY_RESULT_KEY = "SEARCH_COMPANY_RESULT_KEY"
        private val ARGUMENT_KEY = "ARGUMENT_KEY"
        fun newIntent(context: Context, result: String): Intent {
            return Intent(context, SearchCompanyResultActivity::class.java)
                .putExtra(ARGUMENT_KEY, Argument(result))
        }
    }

    @Inject
    lateinit var viewModel: SearchCompanyResultViewModel
    private lateinit var binding: ActivitySearchCompanyResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchCompanyResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setText(intent.getParcelableExtra<Argument>(ARGUMENT_KEY)?.result)

        binding.let {
            it.lifecycleOwner = this
            it.searchResultViewModel = this.viewModel
        }
        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.clickState.collect { state ->
                when (state) {
                    SearchCompanyResultViewModel.State.OnClickEmpty -> noResult()
                    is SearchCompanyResultViewModel.State.OnClickCompany -> onClickCompany(state.searchCompanyResult)
                    else -> {}
                }
            }
        }
    }

    private fun noResult() {
        viewModel.noResultClicked()
        startActivity(SearchCompanyNonResultActivity.newIntent(this))
    }

    private fun onClickCompany(searchResult: SearchCompanyResult) {
        setResult(
            Activity.RESULT_OK,
            Intent().putExtra(SEARCH_COMPANY_RESULT_KEY, searchResult.toParcelable())
        )
        finish()
    }

    @Parcelize
    private data class Argument(
        val result: String
    ) : Parcelable
}
