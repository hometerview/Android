package com.ftw.hometerview.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface Dispatcher {
    fun ui(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
}
