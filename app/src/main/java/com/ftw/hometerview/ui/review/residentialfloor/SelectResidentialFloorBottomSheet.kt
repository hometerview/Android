package com.ftw.hometerview.ui.review.residentialfloor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.ftw.domain.entity.Floor
import com.ftw.hometerview.BR
import com.ftw.hometerview.R
import com.ftw.hometerview.adapter.DataBindingRecyclerAdapter
import com.ftw.hometerview.adapter.RecyclerItem
import com.ftw.hometerview.databinding.BottomSheetSelectResidentialFloorBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SelectResidentialFloorBottomSheet : BottomSheetDialogFragment() {
    companion object {
        fun newInstance() = SelectResidentialFloorBottomSheet()
    }

    interface Listener {
        fun onClickFromSelectResidentialFloorBottomSheet(floor: Floor)
    }

    private var _binding: BottomSheetSelectResidentialFloorBinding? = null
    private val binding get() = _binding!!

    private val adapter = DataBindingRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.bottom_sheet_select_residential_floor,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initList() {
        binding.list.adapter = adapter
        adapter.submitList(getItems())
    }

    private fun getItems(): List<RecyclerItem> {
        return listOf(
            ResidentialFloorItem(
                floor = Floor.HIGH,
                text = getString(R.string.residential_floor_high),
                onClick = {
                    (parentFragment as? Listener)?.onClickFromSelectResidentialFloorBottomSheet(
                        Floor.HIGH
                    )
                    dismissAllowingStateLoss()
                }
            ),
            ResidentialFloorItem(
                floor = Floor.HIGH,
                text = getString(R.string.residential_floor_middle),
                onClick = {
                    (parentFragment as? Listener)?.onClickFromSelectResidentialFloorBottomSheet(
                        Floor.MIDDLE
                    )
                    dismissAllowingStateLoss()
                }
            ),
            ResidentialFloorItem(
                floor = Floor.HIGH,
                text = getString(R.string.residential_floor_low),
                onClick = {
                    (parentFragment as? Listener)?.onClickFromSelectResidentialFloorBottomSheet(
                        Floor.LOW
                    )
                    dismissAllowingStateLoss()
                }
            ),
            ResidentialFloorItem(
                floor = Floor.HIGH,
                text = getString(R.string.residential_floor_private),
                onClick = {
                    (parentFragment as? Listener)?.onClickFromSelectResidentialFloorBottomSheet(
                        Floor.PRIVATE
                    )
                    dismissAllowingStateLoss()
                }
            )
        ).map { item ->
            RecyclerItem(
                data = item,
                layoutId = R.layout.list_item_select_residential_floor,
                variableId = BR.item
            )
        }
    }

    data class ResidentialFloorItem(
        val floor: Floor,
        val text: String,
        val onClick: () -> Unit
    ) {
        fun onClick() {
            onClick.invoke()
        }
    }
}
