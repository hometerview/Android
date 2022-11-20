package com.ftw.hometerview.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.ftw.domain.entity.KakaoToken
import com.ftw.hometerview.R
import com.ftw.hometerview.adapter.AnimationAdapter
import com.ftw.hometerview.databinding.ActivityLoginBinding
import com.ftw.hometerview.ui.main.MainActivity
import com.ftw.hometerview.ui.searchcompany.SearchCompanyActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "LoginActivity"

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: LoginViewModel

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    private lateinit var binding: ActivityLoginBinding

    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e(TAG, "카카오계정으로 로그인 실패", error)
        } else if (token != null) {
            Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        guideSetting()
        ObserveStartActivity()

        binding.kakaoLoginButton.setOnClickListener {
            kakaoLogin()
        }
    }

    private fun guideSetting() {
        // 가이드에 사용 될 이미지, 문구
        val guideImgaeList = listOf(R.drawable.onboarding1, R.drawable.onboarding2)
        val guideTextList = listOf(getString(R.string.guide_text1), getString(R.string.guide_text2), getString(R.string.guide_text3))

        binding.guideViewpager.setPageTransformer { page, position ->
            page.translationX = position
        }

        // 몇 개의 페이지를 미리 로드 해둘것인지
        binding.guideViewpager.offscreenPageLimit = 1
        // 어댑터 생성 (Animation꺼 재활용 했습니다)
        binding.guideViewpager.adapter = AnimationAdapter(guideImgaeList, guideTextList)
        // 방향을 가로로
        binding.guideViewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        // indicator 설정
        binding.dotsIndicator.setViewPager2(binding.guideViewpager)
    }

    private fun ObserveStartActivity(){
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when(state) {
                    LoginViewModel.State.Success -> {
                        StartSearchCompanyActivity()
                    }
                    LoginViewModel.State.Failure -> {
                        toastMessage("로그인에 실패하셨습니다")
                    }
                    else -> {

                    }
                }
            }
        }
    }
    private fun StartSearchCompanyActivity(){
        startActivity(MainActivity.newIntent(this))
        finish()
    }

    private fun toastMessage(message : String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun kakaoLogin() {
        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡으로 로그인 실패", error)
                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        toastMessage("권한이 필요하므로, 다시 한번 시도해주세요!")
                        return@loginWithKakaoTalk
                    }
                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                } else if (token != null) {
                    Log.i(TAG, "카카오톡으로 로그인 성공 token = ${token.accessToken}")
                    viewModel.setKakaoToken(KakaoToken(token.accessToken,token.refreshToken))
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }
}
