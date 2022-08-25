package com.ftw.hometerview.ui.review

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.ActivityCreateReviewBinding
import com.ftw.hometerview.extension.addFragment
import com.ftw.hometerview.extension.replaceFragment
import com.ftw.hometerview.ui.review.first.CreateReviewFirstStepFragment
import com.ftw.hometerview.ui.review.second.CreateReviewSecondStepFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateReviewActivity :
    AppCompatActivity(),
    CreateReviewFirstStepFragment.Listener,
    CreateReviewSecondStepFragment.Listener {

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, CreateReviewActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityCreateReviewBinding>(this, R.layout.activity_create_review)
        replaceFragment(R.id.fragment_container_view, CreateReviewFirstStepFragment.newInstance(), false)
    }

    override fun onClickAddressFromFirstStep(address: String) {
        addFragment(R.id.fragment_container_view, CreateReviewSecondStepFragment.newInstance(address), true)
    }

    override fun onClickNextFromSecondStep(address: String, floor: String) {
        addFragment(R.id.fragment_container_view, CreateReviewFirstStepFragment.newInstance(), true)
    }
}
