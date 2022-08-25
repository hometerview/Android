package com.ftw.hometerview.ui.myreviews

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
import com.ftw.hometerview.databinding.ActivityMyReviewsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MyReviewsActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MyReviewsActivity::class.java)
        }
    }

    @Inject
    lateinit var viewModel: MyReviewsViewModel

    private lateinit var binding: ActivityMyReviewsBinding

    private val adapter = DataBindingRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMyReviewsBinding>(
            this,
            R.layout.activity_my_reviews
        ).apply {
            viewModel = this@MyReviewsActivity.viewModel
        }

        initList()
        observe()
    }

    private fun initList() {
        binding.myReviewRecyclerview.adapter = adapter
        this.let { binding.myReviewRecyclerview.addItemDecoration(SpacingItemDecoration(it)) }
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.myReviewsItems.collect {
                    adapter.submitList(it)
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect { event ->
                    when (event) {
                        MyReviewsViewModel.Event.None -> {}
                        MyReviewsViewModel.Event.onClickWriteReview -> {
                            // ㄹㅣ뷰 작성 화면으로 이동
                        }
                    }
                }
            }
        }
    }
}
