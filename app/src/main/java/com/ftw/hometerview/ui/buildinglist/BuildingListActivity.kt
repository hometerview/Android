package com.ftw.hometerview.ui.buildinglist

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ftw.hometerview.adapter.DataBindingRecyclerAdapter
import com.ftw.hometerview.adapter.DividerItemDecoration
import com.ftw.hometerview.databinding.ActivityBuildingListBinding
import com.ftw.hometerview.ui.main.home.review.LocationReviewListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class BuildingListActivity : AppCompatActivity() {

    companion object {
        val ARGUMENT_KEY = "ARGUMENT_KEY"
        fun newIntent(context: Context, location: String, cnt: Int): Intent {
            return Intent(context, BuildingListActivity::class.java)
                .putExtra(ARGUMENT_KEY, Argument(location, cnt))
        }
    }

    private lateinit var binding: ActivityBuildingListBinding
    private val adapter = DataBindingRecyclerAdapter()

    @Inject
    @Named("BuildingList")
    lateinit var viewModel: LocationReviewListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBuildingListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val argument = intent.getParcelableExtra<Argument>(ARGUMENT_KEY) ?: return

        binding.cntTextview.text = argument.cnt.toString()
        binding.locationTextview.text = "${argument.location} 건물"

        initList()
        observe()
        viewModel.setLocation(argument.location)

    }

    private fun initList() {
        binding.list.adapter = adapter
        baseContext?.let { binding.list.addItemDecoration(DividerItemDecoration(it)) }
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

    @Parcelize
    private data class Argument(
        val location: String,
        val cnt: Int
    ) : Parcelable

}
