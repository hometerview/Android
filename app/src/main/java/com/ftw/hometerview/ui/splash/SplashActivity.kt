package com.ftw.hometerview.ui.splash

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ftw.hometerview.R
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
                    SplashViewModel.State.Failure -> showLoginActivity()    // TODO: 로그인 화면으로 이동
                    SplashViewModel.State.Success -> Toast.makeText(this@SplashActivity, "Get token is succeeded", Toast.LENGTH_SHORT).show()    // TODO: MainActivity 로 이동
                    SplashViewModel.State.Loading -> Toast.makeText(this@SplashActivity, "Loading", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showLoginActivity() {
        startActivity(MainActivity.newIntent(this))
        finish()
    }
}
