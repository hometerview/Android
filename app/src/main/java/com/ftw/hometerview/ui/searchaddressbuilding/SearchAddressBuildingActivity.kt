package com.ftw.hometerview.ui.searchaddressbuilding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ftw.hometerview.adapter.DataBindingRecyclerAdapter
import com.ftw.hometerview.adapter.DividerItemDecoration
import com.ftw.hometerview.databinding.ActivitySearchAddressBuildingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchAddressBuildingActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SearchAddressBuildingActivity::class.java)
        }
    }

    private lateinit var binding: ActivitySearchAddressBuildingBinding
    private val adapter = DataBindingRecyclerAdapter()

    @Inject
    lateinit var viewModel: SearchAddressBuildingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchAddressBuildingBinding.inflate(layoutInflater).apply {
            viewModel = this@SearchAddressBuildingActivity.viewModel
        }

        setContentView(binding.root)

        initList()
        observe()
    }

    private fun initList() {
        binding.searchResultRecyclerView.adapter = adapter
        binding.searchResultRecyclerView.addItemDecoration(DividerItemDecoration(this))
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.reviews.collect {
                    adapter.submitList(it)
                }
            }
        }
    }
}
