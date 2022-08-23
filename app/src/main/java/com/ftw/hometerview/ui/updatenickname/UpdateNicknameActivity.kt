package com.ftw.hometerview.ui.updatenickname

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.ActivityUpdateNickNameBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@AndroidEntryPoint
class UpdateNicknameActivity : AppCompatActivity() {

    companion object {
        val UPDATE_NICKNAME_RESULT_KEY = "UPDATE_NICKNAME_RESULT_KEY"
        fun newIntent(context: Context, nickname: String): Intent {
            return Intent(context, UpdateNicknameActivity::class.java)
                .putExtra(UPDATE_NICKNAME_RESULT_KEY, nickname)
        }
    }

    @Inject
    lateinit var viewModel: UpdateNicknameViewModel

    private lateinit var binding: ActivityUpdateNickNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityUpdateNickNameBinding>(
            this,
            R.layout.activity_update_nick_name
        ).apply {
            viewModel = this@UpdateNicknameActivity.viewModel
        }

        val nickname = intent.getStringExtra(UPDATE_NICKNAME_RESULT_KEY) ?: return
        viewModel.nickname.value = nickname
        observe()
    }

    fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.nickname.collect {
                    binding.lengthTextview.text = getString(R.string.written_nickname_length, it.length)
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect { event ->
                    when (event) {
                        UpdateNicknameViewModel.Event.None -> {}
                        is UpdateNicknameViewModel.Event.OnClickNext -> {
                            onClickNextFromThirdStepSearchCompany(event.nickname)
                        }
                    }
                }
            }
        }
    }

    private fun onClickNextFromThirdStepSearchCompany(nickname: String) {
        setResult(
            Activity.RESULT_OK,
            Intent().putExtra(UPDATE_NICKNAME_RESULT_KEY, nickname)
        )
        finish()
    }

    @Parcelize
    private data class Argument(
        val nickname: String
    ) : Parcelable
}
