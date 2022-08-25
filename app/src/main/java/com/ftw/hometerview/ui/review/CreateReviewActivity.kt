package com.ftw.hometerview.ui.review

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.ActivityCreateReviewBinding
import com.ftw.hometerview.extension.replaceFragment
import com.ftw.hometerview.ui.review.first.CreateReviewFirstStepFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateReviewActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context): Intent = Intent(context, CreateReviewActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityCreateReviewBinding>(this, R.layout.activity_create_review)
        replaceFragment(R.id.frame_layout, CreateReviewFirstStepFragment.newInstance(), false)
    }
}
