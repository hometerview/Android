package com.ftw.hometerview.adapter

import android.view.View.GONE
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ftw.domain.entity.SearchAddressBuildingResult

@BindingAdapter("android:icon")
fun setSearchResultIcon(
    textView: TextView,
    item: SearchAddressBuildingResult
) {
    when (item.type) {
        "지하철역" -> {
            textView.text = "지하철역"
        }
        "지역" -> {
            textView.text = "지역"
        }
        else -> {
            textView.text = "건물"
        }
    }
}

@BindingAdapter("android:address")
fun setSearchResultAddress(
    textView: TextView,
    item: SearchAddressBuildingResult
) {
    when (item.type) {
        "지하철역", "지역"  -> {
            textView.visibility = GONE
        }
        else -> {
            textView.text = item.address
        }
    }
}
