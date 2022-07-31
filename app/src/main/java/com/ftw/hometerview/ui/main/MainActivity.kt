package com.ftw.hometerview.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import com.ftw.domain.usecase.login.LoginUseCase
import com.ftw.domain.usecase.login.LoginUseCaseImpl
import com.ftw.hometerview.R
import kotlinx.parcelize.Parcelize

class MainActivity : AppCompatActivity() {

    companion object {
        val ARGUMENT_KEY = "ARGUMENT_KEY"
        fun newIntent(context: Context, a: String, b: Int): Intent {
            return Intent(context, MainActivity::class.java)
                .putExtra(ARGUMENT_KEY, Argument(a, b))
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        intent.getParcelableExtra<Argument>(ARGUMENT_KEY)?.let {

        } ?: finish()

    }

    @Parcelize
    private data class Argument(
        val a: String,
        val b: Int
    ) : Parcelable
}
