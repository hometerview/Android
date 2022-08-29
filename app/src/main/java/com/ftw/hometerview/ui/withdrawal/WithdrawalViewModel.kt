package com.ftw.hometerview.ui.withdrawal

import com.ftw.hometerview.dispatcher.Dispatcher
import kotlinx.coroutines.flow.*

class WithdrawalViewModel(
    dispatcher: Dispatcher
) {

    val withdrawalFirstCheck: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val withdrawalSecondCheck: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val withdrawalThirdCheck: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val withdrawalCheck: MutableStateFlow<Boolean> = MutableStateFlow(false)


    fun onClickWithdrawalFirstCheck() {
        withdrawalFirstCheck.value = !withdrawalFirstCheck.value
        withdrawalCheck.value = withdrawalCheck()
    }

    fun onClickWithdrawalSecondCheck() {
        withdrawalSecondCheck.value = !withdrawalSecondCheck.value
        withdrawalCheck.value = withdrawalCheck()
    }

    fun onClickWithdrawalThirdCheck() {
        withdrawalThirdCheck.value = !withdrawalThirdCheck.value
        withdrawalCheck.value = withdrawalCheck()
    }

    private fun withdrawalCheck(): Boolean {
        if (withdrawalFirstCheck.value
            && withdrawalSecondCheck.value
            && withdrawalThirdCheck.value
        ) return true
        return false
    }
}
