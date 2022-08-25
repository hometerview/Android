package com.ftw.hometerview.extension

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.replaceFragment(
    @LayoutRes layoutId: Int,
    fragment: Fragment,
    addToBackStack: Boolean
) {
    supportFragmentManager.beginTransaction()
        .run {
            replace(layoutId, fragment)
            if (addToBackStack) addToBackStack(null)
            commitAllowingStateLoss()
        }
}

fun FragmentActivity.addFragment(
    @LayoutRes layoutId: Int,
    fragment: Fragment,
    addToBackStack: Boolean
) {
    supportFragmentManager.beginTransaction().run {
        supportFragmentManager.findFragmentById(layoutId)?.let { found ->
            hide(found)
        }

        add(layoutId, fragment)
        if (addToBackStack) addToBackStack(null)
        commitAllowingStateLoss()
    }
}
