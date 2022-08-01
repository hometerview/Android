package com.ftw.hometerview.ui.main

import android.view.MenuItem
import com.ftw.hometerview.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel {

    sealed class State {
        object HomeItemSelected : State()
        object MapItemSelected : State()
        object FavoriteItemSelected : State()
        object MyPageItemSelected : State()
        object None : State()
    }

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.HomeItemSelected)
    val state: StateFlow<State> get() = _state.asStateFlow()

    fun onNavigationItemSelected(item: MenuItem): Boolean {
        _state.value = when (item.itemId) {
            R.id.home -> State.HomeItemSelected
            R.id.map -> State.MapItemSelected
            R.id.heart -> State.FavoriteItemSelected
            R.id.user -> State.MyPageItemSelected
            else -> State.HomeItemSelected
        }

        return state.value != State.None
    }
}
