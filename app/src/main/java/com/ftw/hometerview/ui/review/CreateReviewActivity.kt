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
import com.ftw.hometerview.ui.review.first.CreateReviewFirstStepInputAddressFragment
import com.ftw.hometerview.ui.review.first.CreateReviewFirstStepSelectFloorFragment
import com.ftw.hometerview.ui.review.second.CreateReviewSecondStepReviewFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class CreateReviewActivity :
    AppCompatActivity(),
    CreateReviewFirstStepInputAddressFragment.Listener,
    CreateReviewFirstStepSelectFloorFragment.Listener,
    CreateReviewSecondStepReviewFragment.Listener {

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, CreateReviewActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityCreateReviewBinding>(
            this,
            R.layout.activity_create_review
        )
        replaceFragment(
            R.id.fragment_container_view,
            CreateReviewFirstStepInputAddressFragment.newInstance(),
            false
        )
    }

    override fun onClickAddressFromFirstStepAddress(address: String) {
        addFragment(
            R.id.fragment_container_view,
            CreateReviewFirstStepSelectFloorFragment.newInstance(address),
            true
        )
    }

    override fun onClickNextFromFirstStepResidentialFloor(address: String, floor: String) {
        addFragment(
            R.id.fragment_container_view,
            CreateReviewSecondStepReviewFragment.newInstance(),
            true
        )
    }

    override fun onClickNextFromSecondStep(rating: Int, leftAt: Date, advantage: String, disadvantage: String) {
        TODO("Not yet implemented")
    }
}
