package com.ftw.hometerview.ui.main.map

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ftw.domain.entity.BuildingMarker
import com.ftw.domain.entity.StationMarker
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.FragmentMapBinding
import com.ftw.hometerview.ui.buildinglist.BuildingListActivity
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
    private lateinit var markerRootView: View
    private lateinit var stationTextview: TextView
    private lateinit var cntTextview: TextView
    private lateinit var mapView: MapView
    private var currentZoomLevel: ZoomLevel = ZoomLevel.NONE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = MapView(requireActivity())
        binding.mapView.addView(mapView)

        mapView.setPOIItemEventListener(this@MapFragment)
        mapView.setMapViewEventListener(this@MapFragment)

        setCustomMarkerView()
        sampleStationMarkerItems()

        // 중심점 변경 + 줌 레벨 변경
        mapView.setMapCenterPointAndZoomLevel(
            MapPoint.mapPointWithGeoCoord(
                37.50745434356066, 127.03391894910082
            ), 4, true
        )

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

    private fun setCustomMarkerView() {
        markerRootView =
            LayoutInflater.from(requireContext()).inflate(R.layout.map_item_station_marker, null)
        stationTextview = markerRootView.findViewById(R.id.station_textview) as TextView
        cntTextview = markerRootView.findViewById(R.id.cnt_textview) as TextView
    }

    private fun sampleStationMarkerItems() {
        // 이곳에서 통신 : 역에 따른 리스트, MapPointBounds를 보낸다 생각
        viewModel.setLocation("강남구")
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.marker.collect {
                    for (stationMarker in it) {
                        addStationMarker(stationMarker)
                    }
                }
            }
        }
    }

    private fun addStationMarker(stationMarker: StationMarker) {
        val customMarker = MapPOIItem()
        customMarker.itemName = "${stationMarker.station} ${stationMarker.buildingCnt}개의 집터뷰"

        stationTextview.text = stationMarker.station
        cntTextview.text = stationMarker.buildingCnt.toString()

        customMarker.tag = 1
        customMarker.userObject = stationMarker
        customMarker.mapPoint =
            MapPoint.mapPointWithGeoCoord(stationMarker.latitude, stationMarker.longitude)
        customMarker.markerType = MapPOIItem.MarkerType.CustomImage // 마커타입을 커스텀 마커로 지정.

        customMarker.customImageBitmap = createDrawableFromView(requireActivity(), markerRootView)
        customMarker.isCustomImageAutoscale =
            false // hdpi, xhdpi 등 안드로이드 플랫폼의 스케일을 사용할 경우 지도 라이브러리의 스케일 기능을 꺼줌.

        customMarker.setCustomImageAnchor(
            0.5f,
            0.8f
        ) // 마커 이미지중 기준이 되는 위치(앵커포인트) 지정 - 마커 이미지 좌측 상단 기준 x(0.0f ~ 1.0f), y(0.0f ~ 1.0f) 값.

        mapView.addPOIItem(customMarker)

    }

    private fun setBuildingMarkerView() {
        markerRootView =
            LayoutInflater.from(requireContext()).inflate(R.layout.map_item_building_marker, null)
    }

    private fun sampleBuildingMarkerItems() {
        // 이곳에서 통신 : 해당역에 따른 리스트, MapPointBounds를 보낸다 생각
        viewModel.setBuildingLocation("역삼역")
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.buildingMarker.collect {
                    for (stationMarker in it) {
                        addBuildingMarker(stationMarker)
                    }
                }
            }
        }
    }

    private fun addBuildingMarker(buildingMarker: BuildingMarker) {
        val customMarker = MapPOIItem()
        customMarker.itemName = "${buildingMarker.buildingName}의 집터뷰"

        customMarker.tag = 1
        customMarker.userObject = buildingMarker
        customMarker.mapPoint =
            MapPoint.mapPointWithGeoCoord(buildingMarker.latitude, buildingMarker.longitude)
        customMarker.markerType = MapPOIItem.MarkerType.CustomImage // 마커타입을 커스텀 마커로 지정.

        customMarker.customImageBitmap = createDrawableFromView(requireActivity(), markerRootView)
        customMarker.isCustomImageAutoscale =
            false // hdpi, xhdpi 등 안드로이드 플랫폼의 스케일을 사용할 경우 지도 라이브러리의 스케일 기능을 꺼줌.

        customMarker.setCustomImageAnchor(
            0.5f,
            0.8f
        ) // 마커 이미지중 기준이 되는 위치(앵커포인트) 지정 - 마커 이미지 좌측 상단 기준 x(0.0f ~ 1.0f), y(0.0f ~ 1.0f) 값.

        mapView.addPOIItem(customMarker)
    }

    // View를 Bitmap으로 변환
    private fun createDrawableFromView(context: Context, view: View): Bitmap {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.currentWindowMetrics
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels)
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels)
        view.rootView
        val bitmap: Bitmap = Bitmap.createBitmap(
            view.measuredWidth,
            view.measuredHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    // 마커 리스너
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

    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {}
    override fun onCalloutBalloonOfPOIItemTouched(
        p0: MapView?,
        p1: MapPOIItem?,
        p2: MapPOIItem.CalloutBalloonButtonType?
    ) {
    }

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {}


    // 지도 리스너
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

        // 2 > 7
        if (p0.zoomLevel >= 4 && currentZoomLevel != ZoomLevel.LEVEL2) {
            p0.removeAllPOIItems()
            setCustomMarkerView()
            sampleStationMarkerItems()
            currentZoomLevel = ZoomLevel.LEVEL2
        } else if (p0.zoomLevel == 2 && currentZoomLevel != ZoomLevel.LEVEL1) {
            p0.removeAllPOIItems()
            setBuildingMarkerView()
            sampleBuildingMarkerItems()
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

enum class ZoomLevel {
    NONE, LEVEL1, LEVEL2, LEVEL3
}
