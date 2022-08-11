package com.ftw.hometerview.ui.review

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ftw.hometerview.databinding.ActivityCreateReviewBinding

class CreateReviewActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context): Intent = Intent(context, CreateReviewActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCreateReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
