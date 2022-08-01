package com.ftw.hometerview.ui.onboarding

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import com.ftw.hometerview.databinding.ActivityOnboardingBinding
import com.ftw.hometerview.ui.onboardingresult.OnboardingResultActivity

class OnboardingActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, OnboardingActivity::class.java)
        }
    }

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.searchButton.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                // 보내기 동작
                startActivity(
                    OnboardingResultActivity.newIntent(
                        this, binding.searchButton.text.toString()
                    )
                )
            }
            true
        }
    }

}
