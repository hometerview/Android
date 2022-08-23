package com.ftw.hometerview.ui.main.mypage

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.FragmentMyPageBinding
import com.ftw.hometerview.ui.updatenickname.UpdateNicknameActivity
import com.ftw.hometerview.ui.writtenreview.WrittenReviewsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MyPageFragment : Fragment() {

    private lateinit var updateNicknameLauncher: ActivityResultLauncher<Intent>

    companion object {
        fun newInstance() = MyPageFragment()
    }

    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: MyPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate<FragmentMyPageBinding?>(
            inflater,
            R.layout.fragment_my_page,
            container,
            false
        ).apply {
            viewModel = this@MyPageFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLauncher()
        observeEvent()
        observeShowBanner()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeEvent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect { event ->
                    when (event) {
                        MyPageViewModel.Event.None -> {}
                        is MyPageViewModel.Event.onClickUpdateNickname -> {
                            updateNicknameActivity(event.nickname)
                        }
                        MyPageViewModel.Event.onClickWrittenReview -> {
                            writtenReviewActivity()
                        }
                    }
                }
            }
        }
    }

    private fun observeShowBanner() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.showPopup.collect { showBanner ->

                    binding.updateCompletePopup.isVisible = showBanner
                    if (showBanner) {
                        binding.updateCompletePopup.apply {

                            visibility = View.INVISIBLE

                            val appearAnimation =
                                AnimationUtils.loadAnimation(requireContext(), R.anim.appear_popup)
                            val fadeoutAnimation =
                                AnimationUtils.loadAnimation(requireContext(), R.anim.anim_fade_out)

                            appearAnimation.setAnimationListener(object :
                                Animation.AnimationListener {
                                override fun onAnimationStart(arg0: Animation) {
                                    visibility = View.VISIBLE
                                }
                                override fun onAnimationRepeat(arg0: Animation) {}
                                override fun onAnimationEnd(arg0: Animation) {
                                    startAnimation(fadeoutAnimation)
                                }
                            })

                            fadeoutAnimation.setAnimationListener(object :
                                Animation.AnimationListener {
                                override fun onAnimationStart(arg0: Animation) {}
                                override fun onAnimationRepeat(arg0: Animation) {}
                                override fun onAnimationEnd(arg0: Animation) {
                                    visibility = View.GONE
                                    viewModel.showPopup.value = false
                                }
                            })

                            startAnimation(appearAnimation)
                        }
                    }
                }
            }
        }
    }

    private fun updateNicknameActivity(nickname: String) {
        updateNicknameLauncher.launch(UpdateNicknameActivity.newIntent(requireContext(), nickname))
    }

    private fun writtenReviewActivity() {
        startActivity(WrittenReviewsActivity.newIntent(requireContext()))
    }

    private fun setLauncher() {
        updateNicknameLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode != Activity.RESULT_OK) return@registerForActivityResult
                val nickname =
                    result.data?.getStringExtra(UpdateNicknameActivity.UPDATE_NICKNAME_ARGUMENT_KEY)
                if (nickname == null) return@registerForActivityResult
                viewModel.showPopup.value = true
            }
    }
}
