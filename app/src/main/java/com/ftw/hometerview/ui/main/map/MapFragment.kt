package com.ftw.hometerview.ui.main.map

import android.annotation.SuppressLint
import android.content.Context.LOCATION_SERVICE
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ftw.domain.entity.BuildingMarker
import com.ftw.domain.entity.StationMarker
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.FragmentMapBinding
import com.ftw.hometerview.databinding.MapItemBuildingMarkerBinding
import com.ftw.hometerview.databinding.MapItemStationMarkerBinding
import com.ftw.hometerview.ui.buildinglist.BuildingListActivity
import com.ftw.hometerview.ui.buildingreview.BuildingReviewActivity
import com.ftw.hometerview.ui.main.map.maputil.createDrawable
import com.ftw.hometerview.ui.searchaddressbuilding.SearchAddressBuildingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import javax.inject.Inject

@AndroidEntryPoint
class MapFragment : Fragment(), MapView.POIItemEventListener, MapView.MapViewEventListener {

    companion object {
        fun newInstance() = MapFragment()
    }

    @Inject
    lateinit var viewModel: MapViewModel

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private var _markerBinding: MapItemStationMarkerBinding? = null
    private val markerBinding get() = _markerBinding!!
    private var _buildingMarkerBinding: MapItemBuildingMarkerBinding? = null
    private val buildingMarkerBinding get() = _buildingMarkerBinding!!

    private lateinit var mapView: MapView
    private var currentZoomLevel: ZoomLevel = ZoomLevel.NONE
    private var stationMarkers: List<StationMarker> = emptyList()
    private var buildingMarkers: List<BuildingMarker> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMap()
        sampleStationMarkerItems()
        sampleBuildingMarkerItems()

        binding.nowLocationButton.setOnClickListener {
            startTracking()
        }

        binding.searchStationBuildingButton.setOnClickListener {
            requireContext().startActivity(SearchAddressBuildingActivity.newIntent(requireContext()))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initMap() {
        mapView = MapView(requireActivity())
        mapView.setPOIItemEventListener(this@MapFragment)
        mapView.setMapViewEventListener(this@MapFragment)
        currentZoomLevel = ZoomLevel.NONE
        mapView.setMapCenterPointAndZoomLevel(
            MapPoint.mapPointWithGeoCoord(
                37.50745434356066, 127.03391894910082
            ), currentZoomLevel.zoom, true
        )
        binding.mapView.addView(mapView)
    }

    private fun sampleStationMarkerItems() {
        viewModel.setLocation("강남구")
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.marker.collect {
                    stationMarkers = it
                }
            }
        }
    }

    private fun sampleBuildingMarkerItems() {
        viewModel.setBuildingLocation("역삼역")
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.buildingMarker.collect {
                    buildingMarkers = it
                }
            }
        }
    }

    private fun addStationMarker(stationMarker: StationMarker) {

        _markerBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.map_item_station_marker, null, false)

        markerBinding.let {
            it.stationTextview.text = stationMarker.station
            it.cntTextview.text = stationMarker.buildingCnt.toString()
        }

        val customMarker = makeMarker(
            itemName = "${stationMarker.station} ${stationMarker.buildingCnt}개의 집터뷰",
            userObject = stationMarker,
            latitude = stationMarker.latitude,
            longitude = stationMarker.longitude,
            isCustomImageAutoscale = false,
            ratioFromTopLeftOriginX = 0.5f,
            ratioFromTopLeftOriginY = 0.8f
        ).apply {
            customImageBitmap = markerBinding.root.createDrawable()
        }

        mapView.addPOIItem(customMarker)
    }

    private fun addBuildingMarker(buildingMarker: BuildingMarker) {

        _buildingMarkerBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.map_item_building_marker, null, false)

        buildingMarkerBinding.cntTextview.text = buildingMarker.reviewCnt.toString()
        
        val customMarker = makeMarker(
            itemName = "${buildingMarker.buildingName}의 집터뷰",
            userObject = buildingMarker,
            latitude = buildingMarker.latitude,
            longitude = buildingMarker.longitude,
            isCustomImageAutoscale = false,
            ratioFromTopLeftOriginX = 0.5f,
            ratioFromTopLeftOriginY = 0.8f
        ).apply {
            customImageBitmap = buildingMarkerBinding.root.createDrawable()
        }

        mapView.addPOIItem(customMarker)
    }


    private fun makeMarker(
        itemName: String,
        userObject: Any,
        latitude: Double,
        longitude: Double,
        isCustomImageAutoscale: Boolean,
        ratioFromTopLeftOriginX: Float,
        ratioFromTopLeftOriginY: Float
        ): MapPOIItem {

        val customMarker = MapPOIItem()
        customMarker.itemName = itemName
        customMarker.userObject = userObject
        customMarker.mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude)
        // 마커타입을 커스텀 마커로 지정.
        customMarker.markerType = MapPOIItem.MarkerType.CustomImage
        // hdpi, xhdpi 등 안드로이드 플랫폼의 스케일을 사용할 경우 지도 라이브러리의 스케일 기능을 꺼줌.
        customMarker.isCustomImageAutoscale = isCustomImageAutoscale
        // 마커 이미지중 기준이 되는 위치(앵커포인트) 지정 - 마커 이미지 좌측 상단 기준 x(0.0f ~ 1.0f), y(0.0f ~ 1.0f) 값.
        customMarker.setCustomImageAnchor(ratioFromTopLeftOriginX, ratioFromTopLeftOriginY)

        return customMarker
    }

    override fun onPOIItemSelected(p0: MapView, p1: MapPOIItem) {
        if (p0.zoomLevel >= 4) {
            val stationMarker: StationMarker = p1.userObject as StationMarker
            binding.buildingListButton.apply {
                visibility = INVISIBLE
                val animation =
                    AnimationUtils.loadAnimation(requireContext(), R.anim.appear_translate)
                animation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(arg0: Animation) {
                        visibility = VISIBLE
                    }

                    override fun onAnimationRepeat(arg0: Animation) {}
                    override fun onAnimationEnd(arg0: Animation) {
                        text = "${stationMarker.station} ${stationMarker.buildingCnt}개 건물 보기"
                        setOnClickListener {
                            requireContext().startActivity(
                                BuildingListActivity.newIntent(
                                    requireContext(),
                                    stationMarker.station,
                                    stationMarker.buildingCnt
                                )
                            )
                        }
                        p0.setMapCenterPointAndZoomLevel(p1.mapPoint, 2, true)
                    }
                })
                startAnimation(animation)
            }
        }
    }
    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {}
    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {
        if(p1 != null && p1.userObject is BuildingMarker){
            val buildingMarker: BuildingMarker = p1.userObject as BuildingMarker
            startActivity(BuildingReviewActivity.newIntent(requireContext(),
                buildingMarker.buildingId.toLong()
            ))
        }

    }
    override fun onCalloutBalloonOfPOIItemTouched(
        p0: MapView?,
        p1: MapPOIItem?,
        p2: MapPOIItem.CalloutBalloonButtonType?
    ) {}

    override fun onMapViewInitialized(p0: MapView?) {}
    override fun onMapViewCenterPointMoved(p0: MapView?, p1: MapPoint?) {}
    override fun onMapViewSingleTapped(p0: MapView?, p1: MapPoint?) {}
    override fun onMapViewDoubleTapped(p0: MapView?, p1: MapPoint?) {}
    override fun onMapViewLongPressed(p0: MapView?, p1: MapPoint?) {}
    override fun onMapViewDragStarted(p0: MapView?, p1: MapPoint?) {}
    override fun onMapViewDragEnded(p0: MapView?, p1: MapPoint?) {}
    override fun onMapViewMoveFinished(p0: MapView?, p1: MapPoint?) {}
    override fun onMapViewZoomLevelChanged(p0: MapView, p1: Int) {
        if (p0.zoomLevel > 2) {
            binding.buildingListButton.apply {
                val animation =
                    AnimationUtils.loadAnimation(requireContext(), R.anim.disappear_translate)
                animation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(arg0: Animation) {}
                    override fun onAnimationRepeat(arg0: Animation) {}
                    override fun onAnimationEnd(arg0: Animation) {
                        visibility = GONE
                    }
                })
                startAnimation(animation)
            }
        }

        if (p0.zoomLevel >= 4 && (currentZoomLevel != ZoomLevel.LEVEL2 || currentZoomLevel == ZoomLevel.NONE) ) {
            p0.removeAllPOIItems()
            stationMarkers.forEach { marker -> addStationMarker(marker) }
            currentZoomLevel = ZoomLevel.LEVEL2
        } else if (p0.zoomLevel == 2 && currentZoomLevel != ZoomLevel.LEVEL1) {
            p0.removeAllPOIItems()
            buildingMarkers.forEach { addBuildingMarker(it) }
            currentZoomLevel = ZoomLevel.LEVEL1
        }
    }

    @SuppressLint("MissingPermission")
    private fun startTracking() {
        mapView.currentLocationTrackingMode =
            MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading  //이 부분

        val lm: LocationManager =
            requireContext().getSystemService(LOCATION_SERVICE) as LocationManager
        val userNowLocation: Location? = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        //위도 , 경도
        val uLatitude = userNowLocation?.latitude
        val uLongitude = userNowLocation?.longitude
        val uNowPosition = MapPoint.mapPointWithGeoCoord(uLatitude!!, uLongitude!!)

        // 현 위치에 마커 찍기
        val marker = MapPOIItem()
        marker.itemName = "현 위치"
        marker.mapPoint = uNowPosition
        marker.markerType = MapPOIItem.MarkerType.BluePin
        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin
        mapView.addPOIItem(marker)
    }
}

enum class ZoomLevel(val zoom: Int) {
    NONE(4), LEVEL1(2), LEVEL2(4), LEVEL3(6)
}
