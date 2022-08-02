package com.ftw.hometerview.ui.main.home.review

import com.ftw.domain.entity.LocationReview
import com.ftw.domain.usecase.review.GetLocaionReviewsUseCase
import com.ftw.hometerview.dispatcher.Dispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LocationListViewModel(
    dispatcher: Dispatcher,
    getLocationReviewsUseCase: GetLocaionReviewsUseCase
) {

    private val location: MutableStateFlow<String> = MutableStateFlow("")

    private val _reviews: MutableStateFlow<List<LocationReview>> = MutableStateFlow(emptyList())
    val reviews: StateFlow<List<LocationReview>> = _reviews.asStateFlow()

    fun setLocation(location: String) {
        this.location.value = location
    }
}
