package com.ftw.hometerview.ui.onboardingresult

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.ftw.hometerview.databinding.ActivityOnboardingResultBinding
import com.ftw.hometerview.ui.login.TAG
import com.ftw.hometerview.ui.onboardingnonresult.OnboardingNonResultActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingResultActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: OnboardingResultViewModel
    private lateinit var binding: ActivityOnboardingResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnboardingResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setText(intent.getStringExtra("search_word"))

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
        Log.d(TAG, "카카오계정공")
        viewModel.noResultClicked()
        val intent = Intent(this, OnboardingNonResultActivity::class.java)
        startActivity(intent)    // TODO:  HometerviewActivity로 이동
    }
}
