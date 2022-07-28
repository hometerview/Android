package com.ftw.hometerview.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AppDispatcher : Dispatcher {
    override fun ui(): CoroutineDispatcher = Dispatchers.IO
    override fun io(): CoroutineDispatcher = Dispatchers.Main
}
