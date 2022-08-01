package com.ftw.hometerview.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ftw.hometerview.R
import kotlinx.parcelize.Parcelize
import com.ftw.hometerview.databinding.ActivityMainBinding
import com.ftw.hometerview.ui.main.favorite.FavoriteFragment
import com.ftw.hometerview.ui.main.home.HomeFragment
import com.ftw.hometerview.ui.main.map.MapFragment
import com.ftw.hometerview.ui.main.mypage.MyPageFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        val ARGUMENT_KEY = "ARGUMENT_KEY"
        fun newIntent(context: Context, a: String, b: Int): Intent {
            return Intent(context, MainActivity::class.java)
                .putExtra(ARGUMENT_KEY, Argument(a, b))
        }
    }


    companion object {
        fun newIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    @Inject
    lateinit var viewModel: MainViewModel

    private val homeFragment by lazy { HomeFragment() }
    private val mapFragment by lazy { MapFragment() }
    private val favoriteFragment by lazy { FavoriteFragment() }
    private val myPageFragment by lazy { MyPageFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getParcelableExtra<Argument>(ARGUMENT_KEY)?.let {

        } ?: finish()

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                viewModel = this@MainActivity.viewModel
                lifecycleOwner = this@MainActivity
            }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        MainViewModel.State.HomeItemSelected -> replaceFragment(homeFragment)
                        MainViewModel.State.MapItemSelected -> replaceFragment(mapFragment)
                        MainViewModel.State.FavoriteItemSelected -> replaceFragment(favoriteFragment)
                        MainViewModel.State.MyPageItemSelected -> replaceFragment(myPageFragment)
                        MainViewModel.State.None -> throw IllegalArgumentException()
                    }
                }
            }
        }
    }


    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commitAllowingStateLoss()
    }

    @Parcelize
    private data class Argument(
        val a: String,
        val b: Int
    ) : Parcelable
}
