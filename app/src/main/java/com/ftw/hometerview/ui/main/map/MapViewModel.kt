package com.ftw.hometerview.ui.main.map

import com.ftw.domain.entity.BuildingMarker
import com.ftw.domain.entity.StationMarker
import com.ftw.domain.usecase.map.GetBuildingMarkerUseCase
import com.ftw.domain.usecase.map.GetMarkerUseCase
import com.ftw.hometerview.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class MapViewModel(
    dispatcher: Dispatcher,
    getMarkerUseCase: GetMarkerUseCase,
    getBuildingMarkerUseCase: GetBuildingMarkerUseCase
) {
    private val location: MutableStateFlow<String> = MutableStateFlow("")
    private val buildingLocation: MutableStateFlow<String> = MutableStateFlow("")

    val marker: StateFlow<List<StationMarker>> = location.filter { it.isNotBlank() }
        .transformLatest { location ->
            flow<List<StationMarker>> {
                emit(
                    getMarkerUseCase(location)
                )
            }
                .collect {
                    emit(it)
                }
        }
        .stateIn(CoroutineScope(dispatcher.ui()), SharingStarted.Eagerly, emptyList())

    val buildingMarker: StateFlow<List<BuildingMarker>> = buildingLocation.filter { it.isNotBlank() }
        .transformLatest { location ->
            flow<List<BuildingMarker>> {
                emit(
                    getBuildingMarkerUseCase(location)
                )
            }
                .collect {
                    emit(it)
                }
        }
        .stateIn(CoroutineScope(dispatcher.ui()), SharingStarted.Eagerly, emptyList())


    fun setLocation(location: String) {
        this.location.value = location
    }

    fun setBuildingLocation(buildingLocation: String) {
        this.buildingLocation.value = buildingLocation
    }
}
