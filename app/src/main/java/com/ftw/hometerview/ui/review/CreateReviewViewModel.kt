package com.ftw.hometerview.ui.review

import android.util.Log
import com.ftw.domain.usecase.review.CreateReviewUseCase
import com.ftw.hometerview.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class CreateReviewViewModel(
    private val dispatcher: Dispatcher,
    private val createReviewUseCase: CreateReviewUseCase
) {
    private var buildingId: String? = null
    private var companyId: String? = null
    private var period: String? = null
    private var rating: Int? = null
    private var advantage: String? = null
    private var disadvantage: String? = null
    private var floor: String? = null

    fun setBuildingId(buildingId: String) {
        this.buildingId = buildingId
    }

    fun setFloor(floor: String) {
        this.floor = floor
    }

    fun setInfo(rating: Int, period: String, advantage: String, disadvantage: String) {
        this.rating = rating
        this.period = period
        this.advantage = advantage
        this.disadvantage = disadvantage
    }

    fun setCompanyId(companyId: String) {
        this.companyId = companyId
    }

    fun create() {
        CoroutineScope(dispatcher.ui()).launch {
            flow {
                emit(
                    createReviewUseCase(
                        buildingId = buildingId ?: "",
                        companyId = companyId ?: "",
                        period = period ?: "",
                        rating = rating ?: 0,
                        advantage = advantage ?: "",
                        disadvantage = disadvantage ?: "",
                        floor = floor ?: ""
                    )
                )
            }.collect {
                Log.d("CreateReviewViewModel", "completed")
            }
        }
    }
}
