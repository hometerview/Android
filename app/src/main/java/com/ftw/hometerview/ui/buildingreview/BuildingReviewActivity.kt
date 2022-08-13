package com.ftw.hometerview.ui.buildingreview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ftw.hometerview.R
import com.ftw.hometerview.adapter.DataBindingRecyclerAdapter
import com.ftw.hometerview.adapter.DividerItemDecoration
import com.ftw.hometerview.databinding.ActivityBuildingReviewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize

@AndroidEntryPoint
class BuildingReviewActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context, buildingId: Long): Intent {
            return Intent(context, BuildingReviewActivity::class.java)
        }
    }

    private lateinit var binding: ActivityBuildingReviewBinding
    private val adapter = DataBindingRecyclerAdapter()

    @Inject
    lateinit var viewModel: BuildingReviewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityBuildingReviewBinding>(
            this,
            R.layout.activity_building_review
        ).apply {
            viewModel = this@BuildingReviewActivity.viewModel
        }

        initList()
        observe()
    }

    private fun initList() {
        binding.list.adapter = adapter
        binding.list.addItemDecoration(DividerItemDecoration(this))
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.items.collect {
                    Log.d("BuildingReviewActivity", "item size: ${it.size}")
                    adapter.submitList(it)
                }
            }
        }
    }

    @Parcelize
    data class Argument(
        val buildingId: Long
    ) : Parcelable
}