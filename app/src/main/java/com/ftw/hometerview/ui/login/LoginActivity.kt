package com.ftw.hometerview.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.ftw.hometerview.R
import com.ftw.hometerview.adapter.AnimationAdapter
import com.ftw.hometerview.databinding.ActivityLoginBinding
import com.ftw.hometerview.ui.main.MainActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

const val TAG = "LoginActivity"

class LoginActivity : AppCompatActivity() {

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

        guideSetting()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.kakaoLoginButton.setOnClickListener {
            kakaoLogin()
        }

    }

    private fun guideSetting() {

        // 가이드에 사용 될 이미지, 문구 
        val guideImgaeList = listOf(R.drawable.icon1, R.drawable.icon2, R.drawable.icon3)
        val guideTextList = listOf(getString(R.string.guide_text1), getString(R.string.guide_text2), getString(R.string.guide_text3))

        binding.viewpager.setPageTransformer { page, position ->
            page.translationX = position
        }

        // 몇 개의 페이지를 미리 로드 해둘것인지
        binding.viewpager.offscreenPageLimit = 1
        // 어댑터 생성 (Animation꺼 재활용 했습니다)
        binding.viewpager.adapter = AnimationAdapter(guideImgaeList, guideTextList)
        // 방향을 가로로
        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        // indicator 설정
        binding.dotsIndicator.setViewPager2(binding.viewpager)
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
                        Toast.makeText(this, "권한이 필요하므로, 다시 한번 시도해주세요!", Toast.LENGTH_SHORT).show()
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                } else if (token != null) {
                    Log.i(TAG, "카카오톡으로 로그인 성공 token = ${token.accessToken}")
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }
}
