package com.ftw.hometerview.ui.onboardingnonresult

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.ActivityOnboardingNonResultBinding
import com.ftw.hometerview.ui.onboardingnonresult.searchaddress.SearchAddressFragment

class OnboardingNonResultActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, OnboardingNonResultActivity::class.java)
        }
    }

    private lateinit var binding: ActivityOnboardingNonResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityOnboardingNonResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.enroll_fragment, SearchAddressFragment()).commit()
    }

}
