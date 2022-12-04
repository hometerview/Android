package com.ftw.hometerview.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ftw.hometerview.R
import com.ftw.hometerview.ui.login.LoginActivity
import com.ftw.hometerview.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    SplashViewModel.State.Failure -> startLoginActivity()
                    SplashViewModel.State.Success -> startMainActivity()
                    else -> { }
                }
            }
        }
    }

    private fun startMainActivity() {
        startActivity(MainActivity.newIntent(this))
        finish()
    }

    private fun startLoginActivity() {
        startActivity(LoginActivity.newIntent(this))
        finish()
    }

}
