package com.ftw.hometerview.ui.loading

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.ActivityLoadingBinding

class LoadingActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoadingActivity::class.java)
        }
    }

    lateinit var binding: ActivityLoadingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load(R.raw.loading).into(binding.loadView);
    }

}
