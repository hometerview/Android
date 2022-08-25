package com.ftw.hometerview.ui.review

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ftw.domain.entity.Review
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.ActivityCreateReviewBinding
import com.ftw.hometerview.extension.addFragment
import com.ftw.hometerview.extension.replaceFragment
import com.ftw.hometerview.ui.model.toParcelable
import com.ftw.hometerview.ui.review.first.CreateReviewFirstStepInputAddressFragment
import com.ftw.hometerview.ui.review.first.CreateReviewFirstStepSelectFloorFragment
import com.ftw.hometerview.ui.review.second.CreateReviewSecondStepReviewFragment
import com.ftw.hometerview.ui.review.third.CreateReviewThirdStepSearchCompanyFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class CreateReviewActivity :
    AppCompatActivity(),
    CreateReviewFirstStepInputAddressFragment.Listener,
    CreateReviewFirstStepSelectFloorFragment.Listener,
    CreateReviewSecondStepReviewFragment.Listener,
    CreateReviewThirdStepSearchCompanyFragment.Listener {

    companion object {
        const val CREATE_REVIEW_RESULT_KEY = "CREATE_REVIEW_RESULT_KEY"
        fun newIntent(context: Context): Intent = Intent(context, CreateReviewActivity::class.java)
    }

    private var review = Review.NONE

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
        review = review.copy(buildingAddress = address)
        addFragment(
            R.id.fragment_container_view,
            CreateReviewFirstStepSelectFloorFragment.newInstance(address),
            true
        )
    }

    override fun onClickNextFromFirstStepResidentialFloor(address: String, floor: String) {
        review = review.copy(buildingAddress = address)
        addFragment(
            R.id.fragment_container_view,
            CreateReviewSecondStepReviewFragment.newInstance(),
            true
        )
    }

    override fun onClickNextFromSecondStepReview(
        rating: Int,
        leftAt: Date,
        advantage: String,
        disadvantage: String
    ) {
        review = review.copy(
            rating = rating,
            leftAt = leftAt,
            advantage = advantage,
            disadvantage = disadvantage
        )
        addFragment(
            R.id.fragment_container_view,
            CreateReviewThirdStepSearchCompanyFragment.newInstance(),
            true
        )
    }

    override fun onClickNextFromThirdStepSearchCompany(company: String) {
        review = review.copy(officeLocation = company)
        setResult(
            Activity.RESULT_OK,
            Intent().putExtra(CREATE_REVIEW_RESULT_KEY, review.toParcelable())
        )
        finish()
    }
}
