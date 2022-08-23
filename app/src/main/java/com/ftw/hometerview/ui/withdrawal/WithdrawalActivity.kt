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
                viewModel.event.collect { event ->
                    when (event) {
                        WithdrawalViewModel.Event.None -> {}
                        WithdrawalViewModel.Event.OnClickWithdrawalFirstCheck -> onClickWithdrawalFirstCheck()
                        WithdrawalViewModel.Event.OnClickWithdrawalSecondCheck -> onClickWithdrawalSecondCheck()
                        WithdrawalViewModel.Event.OnClickWithdrawalThirdCheck -> onClickWithdrawalThirdCheck()
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.withdrawalCheck.collect { showBanner ->
                    binding.withdrawalButton.isEnabled = showBanner
                }
            }
        }
    }

    private fun onClickWithdrawalFirstCheck() {
        if(viewModel.withdrawalFirstCheck.value){
            viewModel.withdrawalFirstCheck.value = false
            binding.withdrawalFirstCheck.setImageResource(R.drawable.icon_check_disabled)
        } else{
            viewModel.withdrawalFirstCheck.value = true
            binding.withdrawalFirstCheck.setImageResource(R.drawable.icon_check_enabled)
        }
        viewModel.withdrawalCheck.value = withdrawalCheck()
    }

    private fun onClickWithdrawalSecondCheck() {
        if(viewModel.withdrawalSecondCheck.value){
            viewModel.withdrawalSecondCheck.value = false
            binding.withdrawalSecondCheck.setImageResource(R.drawable.icon_check_disabled)
        } else{
            viewModel.withdrawalSecondCheck.value = true
            binding.withdrawalSecondCheck.setImageResource(R.drawable.icon_check_enabled)
        }
        viewModel.withdrawalCheck.value = withdrawalCheck()
    }

    private fun onClickWithdrawalThirdCheck() {
        if(viewModel.withdrawalThirdCheck.value){
            viewModel.withdrawalThirdCheck.value = false
            binding.withdrawalThirdCheck.setImageResource(R.drawable.icon_check_disabled)
        } else{
            viewModel.withdrawalThirdCheck.value = true
            binding.withdrawalThirdCheck.setImageResource(R.drawable.icon_check_enabled)
        }
        viewModel.withdrawalCheck.value = withdrawalCheck()
    }
    
    private fun withdrawalCheck(): Boolean {
        if(viewModel.withdrawalFirstCheck.value
            && viewModel.withdrawalSecondCheck.value
            && viewModel.withdrawalThirdCheck.value) return true
        return false
    }
}
