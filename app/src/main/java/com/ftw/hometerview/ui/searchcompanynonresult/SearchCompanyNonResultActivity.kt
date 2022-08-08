package com.ftw.hometerview.ui.searchcompanynonresult

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.ActivitySearchCompanyNonResultBinding
import com.ftw.hometerview.ui.searchcompanynonresult.searchcompanyaddress.SearchCompanyAddressFragment

class SearchCompanyNonResultActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SearchCompanyNonResultActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySearchCompanyNonResultBinding =
            ActivitySearchCompanyNonResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.enroll_fragment, SearchCompanyAddressFragment()).commit()
    }

}
