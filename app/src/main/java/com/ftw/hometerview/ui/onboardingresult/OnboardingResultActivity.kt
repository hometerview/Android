package com.ftw.hometerview.ui.onboardingresult

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.lifecycleScope
import com.ftw.hometerview.databinding.ActivityOnboardingResultBinding
import com.ftw.hometerview.ui.onboardingnonresult.OnboardingNonResultActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingResultActivity : AppCompatActivity() {

    companion object {
        val ARGUMENT_KEY = "ARGUMENT_KEY"
        fun newIntent(context: Context, result: String): Intent {
            return Intent(context, OnboardingResultActivity::class.java)
                .putExtra(ARGUMENT_KEY, Argument(result))
        }
    }

    @Inject
    lateinit var viewModel: OnboardingResultViewModel
    private lateinit var binding: ActivityOnboardingResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnboardingResultBinding.inflate(layoutInflater)
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
                    OnboardingResultViewModel.State.OnClickEmpty -> noResult()
                    else -> {}
                }
            }
        }
    }

    private fun noResult() {
        viewModel.noResultClicked()
        startActivity(OnboardingNonResultActivity.newIntent(this))
    }

    @Parcelize
    private data class Argument(
        val result: String
    ) : Parcelable

}
