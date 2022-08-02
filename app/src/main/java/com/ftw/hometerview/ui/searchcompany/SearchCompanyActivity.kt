package com.ftw.hometerview.ui.searchcompany

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import com.ftw.hometerview.databinding.ActivitySearchCompanyBinding
import com.ftw.hometerview.ui.searchcompanyresult.SearchCompanyResultActivity

class SearchCompanyActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SearchCompanyActivity::class.java)
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySearchCompanyBinding = ActivitySearchCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.searchButton.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                // 보내기 동작
                startActivity(
                    SearchCompanyResultActivity.newIntent(
                        context = this, 
                        result = binding.searchButton.text.toString()
                    )
                )
            }
            true
        }
    }

}
