package com.ftw.hometerview.ui.bindingadapter

import androidx.databinding.BindingAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

@BindingAdapter("onNavigationItemSelected")
fun setOnNavigationItemSelectedListener(
    bottomNavigationView: BottomNavigationView,
    listener: NavigationBarView.OnItemSelectedListener?
) {
    bottomNavigationView.setOnItemSelectedListener(listener)
}
