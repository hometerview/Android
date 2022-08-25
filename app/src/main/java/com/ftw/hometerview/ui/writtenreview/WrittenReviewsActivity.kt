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
import com.ftw.hometerview.adapter.DataBindingRecyclerAdapter
import com.ftw.hometerview.adapter.SpacingItemDecoration
import com.ftw.hometerview.databinding.ActivityWrittenReviewsBinding
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
    lateinit var viewModel: WrittenReviewsViewModel

    private lateinit var binding: ActivityWrittenReviewsBinding

    private val adapter = DataBindingRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityWrittenReviewsBinding>(
            this,
            R.layout.activity_written_reviews
        ).apply {
            viewModel = this@WrittenReviewsActivity.viewModel
        }

        initList()
        observe()
    }

    private fun initList() {
        binding.writtenReviewRecyclerview.adapter = adapter
        this.let { binding.writtenReviewRecyclerview.addItemDecoration(SpacingItemDecoration(it)) }
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.writtenRevieewItems.collect {
                    adapter.submitList(it)
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect { event ->
                    when (event) {
                        WrittenReviewsViewModel.Event.None -> {}
                        WrittenReviewsViewModel.Event.onClickWriteReview -> {
                            // ㄹㅣ뷰 작성 화면으로 이동
                        }
                    }
                }
            }
        }
    }
}
