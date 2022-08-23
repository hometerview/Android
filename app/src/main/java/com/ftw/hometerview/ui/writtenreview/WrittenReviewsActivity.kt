package com.ftw.hometerview.ui.writtenreview

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.ActivityWrittenReviewsBinding
import com.ftw.hometerview.ui.writtenreview.nonreview.NonReviewFragment
import com.ftw.hometerview.ui.writtenreview.writtenreviewlist.WrittenReviewListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WrittenReviewsActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, WrittenReviewsActivity::class.java)
        }
    }

    @Inject
    lateinit var viewModel: WrittenReviewViewModel

    private lateinit var binding: ActivityWrittenReviewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityWrittenReviewsBinding>(
            this,
            R.layout.activity_written_reviews
        ).apply {
            viewModel = this@WrittenReviewsActivity.viewModel
        }
        observe()
    }


    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.writtenRevieewItems.collect {
                    if(it.isEmpty()){
                        replaceNonReviewFragment()
                    } else {
                        replaceWrittenReviewListFragment()
                    }
                }
            }
        }
    }

    private fun replaceWrittenReviewListFragment() {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_view, WrittenReviewListFragment.newInstance()).commit()
    }

    private fun replaceNonReviewFragment() {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_view, NonReviewFragment.newInstance()).commit()
    }
}