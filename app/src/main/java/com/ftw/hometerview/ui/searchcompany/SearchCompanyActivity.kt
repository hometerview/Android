package com.ftw.hometerview.ui.searchcompany

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.ftw.hometerview.databinding.ActivitySearchCompanyBinding
import com.ftw.hometerview.ui.model.ParcelableSearchResult
import com.ftw.hometerview.ui.searchcompanyresult.SearchCompanyResultActivity

class SearchCompanyActivity : AppCompatActivity() {

    companion object {
        const val SEARCH_COMPANY_RESULT_KEY = "SEARCH_COMPANY_RESULT_KEY"
        fun newIntent(context: Context): Intent {
            return Intent(context, SearchCompanyActivity::class.java)
        }
    }

    private val searchCompanyResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode != Activity.RESULT_OK) return@registerForActivityResult
            result.data?.getParcelableExtra<ParcelableSearchResult>(
                SearchCompanyResultActivity.SEARCH_COMPANY_RESULT_KEY
            )?.let { searchResult ->
                setResult(
                    Activity.RESULT_OK,
                    Intent().putExtra(SEARCH_COMPANY_RESULT_KEY, searchResult)
                )
                finish()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySearchCompanyBinding = ActivitySearchCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // 보내기 동작
                searchCompanyResultLauncher.launch(
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
