package com.ftw.hometerview.ui.buildingreview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.util.TypedValue
import android.view.View.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
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
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import java.lang.Math.abs
import javax.inject.Inject

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
            lifecycleOwner = this@BuildingReviewActivity
            viewModel = this@BuildingReviewActivity.viewModel
            setSupportActionBar(toolbar)
            toolbar.visibility = GONE
        }
        initList()
        observe()

        val tv = TypedValue()
        theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)
        val actionBarHeight = resources.getDimensionPixelSize(tv.resourceId)

        var animationListenerIng = false
        binding.appBar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            //  Vertical offset == 0 indicates appBar is fully expanded.
            if (abs(verticalOffset) >= appBarLayout.height - actionBarHeight) {
                if (binding.toolbar.visibility == GONE && !animationListenerIng) {
                    animationListenerIng = true
                    binding.toolbar.apply {
                        visibility = INVISIBLE
                        val animation = AnimationUtils.loadAnimation(context, R.anim.anim_fade_in)
                        animation.setAnimationListener(object : Animation.AnimationListener {
                            override fun onAnimationStart(arg0: Animation) {}
                            override fun onAnimationRepeat(arg0: Animation) {}
                            override fun onAnimationEnd(arg0: Animation) {
                                visibility = VISIBLE
                                animationListenerIng = false
                            }
                        })
                        startAnimation(animation)
                    }
                }
            } else {
                if (binding.toolbar.visibility == VISIBLE == !animationListenerIng) {
                    animationListenerIng = true
                    binding.toolbar.apply {
                        val animation = AnimationUtils.loadAnimation(context, R.anim.anim_fade_out)
                        animation.setAnimationListener(object : Animation.AnimationListener {
                            override fun onAnimationStart(arg0: Animation) {}
                            override fun onAnimationRepeat(arg0: Animation) {}
                            override fun onAnimationEnd(arg0: Animation) {
                                visibility = GONE
                                animationListenerIng = false
                            }
                        })
                        startAnimation(animation)
                    }
                }
            }
        }
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
