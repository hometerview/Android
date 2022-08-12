package com.ftw.hometerview.ui.main.map

import com.ftw.domain.entity.BuildingMarker
import com.ftw.domain.entity.StationMarker
import com.ftw.domain.usecase.map.GetBuildingMarkerUseCase
import com.ftw.domain.usecase.map.GetMarkerUseCase
import com.ftw.hometerview.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MapViewModel(
    dispatcher: Dispatcher,
    getMarkerUseCase: GetMarkerUseCase,
    getBuildingMarkerUseCase: GetBuildingMarkerUseCase
) {
    private val location: MutableStateFlow<String> = MutableStateFlow("")
    private val buildingLocation: MutableStateFlow<String> = MutableStateFlow("")

    private val _marker: MutableStateFlow<List<StationMarker>> = MutableStateFlow(listOf(StationMarker.NONE))
    val marker: StateFlow<List<StationMarker>> = _marker.asStateFlow()
    private val _buildingMarker: MutableStateFlow<List<BuildingMarker>> = MutableStateFlow(listOf(BuildingMarker.NONE))
    val buildingMarker: StateFlow<List<BuildingMarker>> = _buildingMarker.asStateFlow()

    init {
        CoroutineScope(dispatcher.ui()).launch {
            flow {
                emit(getMarkerUseCase(location.toString()))
            }
                .catch { emit(listOf()) }
                .collect {
                    _marker.value = it
                }

            flow {
                emit(getBuildingMarkerUseCase(location.toString()))
            }
                .catch { emit(listOf()) }
                .collect {
                    _buildingMarker.value = it
                }
        }
    }

    fun setLocation(location: String) {
        this.location.value = location
    }

    fun setBuildingLocation(buildingLocation: String) {
        this.buildingLocation.value = buildingLocation
    }
}
