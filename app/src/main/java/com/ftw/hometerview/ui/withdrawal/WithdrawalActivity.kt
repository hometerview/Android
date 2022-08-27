package com.ftw.hometerview.ui.withdrawal

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.ActivityWithdrawalBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WithdrawalActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, WithdrawalActivity::class.java)
        }
    }

    @Inject
    lateinit var viewModel: WithdrawalViewModel

    private lateinit var binding: ActivityWithdrawalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityWithdrawalBinding>(
            this,
            R.layout.activity_withdrawal
        ).apply {
            viewModel = this@WithdrawalActivity.viewModel
        }
        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.withdrawalCheck.collect { showBanner ->
                    binding.withdrawalButton.isEnabled = showBanner
                }
            }
        }
    }
}
