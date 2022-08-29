package com.ftw.hometerview.ui.manageaccount

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.ActivityManageAccountBinding
import com.ftw.hometerview.ui.withdrawal.WithdrawalActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ManageAccountActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ManageAccountActivity::class.java)
        }
    }

    @Inject
    lateinit var viewModel: ManageAccountViewModel

    private lateinit var binding: ActivityManageAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityManageAccountBinding>(
            this,
            R.layout.activity_manage_account
        ).apply {
            viewModel = this@ManageAccountActivity.viewModel
        }
        observe()
    }


    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect { event ->
                    when (event) {
                        ManageAccountViewModel.Event.None -> {}
                        ManageAccountViewModel.Event.onClickServiceUseTerms -> onClickServiceUseTerms()
                        ManageAccountViewModel.Event.onClickUseUserInfo -> onClickUseUserInfo()
                        ManageAccountViewModel.Event.onClickLocationForService -> onClickLocationForService()
                        ManageAccountViewModel.Event.onClickOpenSourceLibrary -> onClickOpenSourceLibrary()
                        ManageAccountViewModel.Event.onClickWithdrawal -> onClickWithdrawal()
                    }
                }
            }
        }
    }

    private fun onClickServiceUseTerms() {    }
    private fun onClickUseUserInfo() {    }
    private fun onClickLocationForService() {    }
    private fun onClickOpenSourceLibrary() {    }
    private fun onClickWithdrawal() {
        startActivity(WithdrawalActivity.newIntent(this))
    }
}
