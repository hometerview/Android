package com.ftw.hometerview.ui.searchaddressbuilding

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ftw.hometerview.databinding.ActivitySearchAddressBuildingBinding

class SearchAddressBuildingActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SearchAddressBuildingActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySearchAddressBuildingBinding = ActivitySearchAddressBuildingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}