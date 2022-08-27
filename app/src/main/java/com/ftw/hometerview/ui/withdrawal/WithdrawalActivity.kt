package com.ftw.hometerview.ui.withdrawal

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.ActivityWithdrawalBinding
import dagger.hilt.android.AndroidEntryPoint
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
            lifecycleOwner = this@WithdrawalActivity
            viewModel = this@WithdrawalActivity.viewModel
        }
    }

}
