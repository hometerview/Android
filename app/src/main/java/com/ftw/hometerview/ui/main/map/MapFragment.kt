package com.ftw.hometerview.ui.main.map

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ftw.domain.entitiy.BuildingMarker
import com.ftw.domain.entitiy.StationMarker
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.FragmentMapBinding
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class MapFragment : Fragment(), MapView.POIItemEventListener, MapView.MapViewEventListener {

    companion object {
        fun newInstance() = MapFragment()
    }

    private lateinit var viewModel: MapViewModel
    private lateinit var binding: FragmentMapBinding
    private lateinit var markerRootView: View
    private lateinit var stationTextview: TextView
    private lateinit var cntTextview: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MapViewModel::class.java]

        binding.mapView.setPOIItemEventListener(this@MapFragment)
        binding.mapView.setMapViewEventListener(this@MapFragment)

        setCustomMarkerView()
        sampleStationMarkerItems()

        // 중심점 변경 + 줌 레벨 변경
        binding.mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(37.50745434356066, 127.03391894910082), 4, true);
    }

    private fun setCustomMarkerView() {
        markerRootView = LayoutInflater.from(requireContext()).inflate(R.layout.map_item_station_marker, null)
        stationTextview = markerRootView.findViewById(R.id.station_textview) as TextView
        cntTextview = markerRootView.findViewById(R.id.cnt_textview) as TextView
    }

    private fun sampleStationMarkerItems() {
        // 이곳에서 통신 : 역에 따른 리스트, MapPointBounds를 보낸다 생각
        val stationMarkerList: ArrayList<StationMarker> = ArrayList()
        stationMarkerList.add(StationMarker("강남구청역", 37.51732701554125, 127.04125331828345, 38))
        stationMarkerList.add(StationMarker("학동역", 37.51450076427542, 127.03188613098999, 24))
        stationMarkerList.add(StationMarker("선정릉역", 37.51042438956853, 127.04385094188316, 24))
        stationMarkerList.add(StationMarker("언주역", 37.50745434356066, 127.03391894910082, 105))
        stationMarkerList.add(StationMarker("신논현역", 37.50468158441123, 127.02446281227596, 1705))
        stationMarkerList.add(StationMarker("강남역", 37.498157604159395, 127.0276271154474, 67))
        stationMarkerList.add(StationMarker("역삼역", 37.500750102391144, 127.0364717670947, 13))

        for (stationMarker in stationMarkerList) {
            addStationMarker(stationMarker)
        }
    }

    private fun addStationMarker(stationMarker: StationMarker) {
        val customMarker = MapPOIItem()
        customMarker.itemName = "${stationMarker.station} ${stationMarker.buildingCnt}개의 집터뷰"

        stationTextview.text = stationMarker.station
        cntTextview.text = stationMarker.buildingCnt.toString()

        customMarker.tag = 1
        customMarker.mapPoint = MapPoint.mapPointWithGeoCoord(stationMarker.latitude,stationMarker.longitude)
        customMarker.markerType = MapPOIItem.MarkerType.CustomImage // 마커타입을 커스텀 마커로 지정.

        customMarker.customImageBitmap = createDrawableFromView(requireContext(), markerRootView)
        customMarker.isCustomImageAutoscale =
            false // hdpi, xhdpi 등 안드로이드 플랫폼의 스케일을 사용할 경우 지도 라이브러리의 스케일 기능을 꺼줌.

        customMarker.setCustomImageAnchor(
            0.5f,
            0.8f
        ) // 마커 이미지중 기준이 되는 위치(앵커포인트) 지정 - 마커 이미지 좌측 상단 기준 x(0.0f ~ 1.0f), y(0.0f ~ 1.0f) 값.

        binding.mapView.addPOIItem(customMarker)

    }

    private fun setBuildingMarkerView() {
        markerRootView = LayoutInflater.from(requireContext()).inflate(R.layout.map_item_building_marker, null)
    }

    private fun sampleBuildingMarkerItems() {
        // 이곳에서 통신 : 해당역에 따른 리스트, MapPointBounds를 보낸다 생각
        val buildingMarkerList: ArrayList<BuildingMarker> = ArrayList()
        buildingMarkerList.add(BuildingMarker("죽암 빌딩", 37.50236738491151, 127.03654606433355, 1))
        buildingMarkerList.add(BuildingMarker("동복 빌딩", 37.501057286645356, 127.03363332712635, 2))
        buildingMarkerList.add(BuildingMarker("정인 빌딩", 37.50065429139799, 127.04018674675399, 3))
        buildingMarkerList.add(BuildingMarker("창성 빌딩", 37.50095326004147, 127.03504691863799, 4))
        buildingMarkerList.add(BuildingMarker("진성 빌딩", 37.49987639741427, 127.03558359012032, 5))
        buildingMarkerList.add(BuildingMarker("근도 빌딩", 37.49864101548692, 127.03877207326005, 6))
        buildingMarkerList.add(BuildingMarker("화원 빌딩", 37.49728970863099, 127.03814940421368, 7))

        for (buildingMarker in buildingMarkerList) {
            addBuildingMarker(buildingMarker)
        }
    }

    private fun addBuildingMarker(buildingMarker: BuildingMarker) {
        val customMarker = MapPOIItem()
        customMarker.itemName = "${buildingMarker.buildingName}의 집터뷰"

        customMarker.tag = 1
        customMarker.mapPoint = MapPoint.mapPointWithGeoCoord(buildingMarker.latitude,buildingMarker.longitude)
        customMarker.markerType = MapPOIItem.MarkerType.CustomImage // 마커타입을 커스텀 마커로 지정.

        customMarker.customImageBitmap = createDrawableFromView(requireContext(), markerRootView)
        customMarker.isCustomImageAutoscale =
            false // hdpi, xhdpi 등 안드로이드 플랫폼의 스케일을 사용할 경우 지도 라이브러리의 스케일 기능을 꺼줌.

        customMarker.setCustomImageAnchor(
            0.5f,
            0.8f
        ) // 마커 이미지중 기준이 되는 위치(앵커포인트) 지정 - 마커 이미지 좌측 상단 기준 x(0.0f ~ 1.0f), y(0.0f ~ 1.0f) 값.

        binding.mapView.addPOIItem(customMarker)
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
        if(p0.zoomLevel >= 4) {
            p0.setMapCenterPointAndZoomLevel(p1.mapPoint, 2, true)
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
        Log.d("ABC", "onMapViewZoomLevelChanged: ${p0.zoomLevel}")
        Log.d("ABC", "onMapViewZoomLevelChanged: $p1")
        if(p0.zoomLevel == 4) {
            p0.removeAllPOIItems()
            setCustomMarkerView()
            sampleStationMarkerItems()
        } else if(p0.zoomLevel == 2) {
            p0.removeAllPOIItems()
            setBuildingMarkerView()
            sampleBuildingMarkerItems()
        }
    }

}
