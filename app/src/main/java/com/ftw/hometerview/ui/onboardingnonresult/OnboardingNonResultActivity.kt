package com.ftw.hometerview.ui.onboardingnonresult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ftw.hometerview.databinding.ActivityOnboardingNonResultBinding

class OnboardingNonResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingNonResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityOnboardingNonResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}