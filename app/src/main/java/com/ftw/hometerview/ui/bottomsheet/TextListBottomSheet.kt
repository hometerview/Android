package com.ftw.hometerview.ui.bottomsheet

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.ftw.hometerview.BR
import com.ftw.hometerview.R
import com.ftw.hometerview.adapter.DataBindingRecyclerAdapter
import com.ftw.hometerview.adapter.RecyclerItem
import com.ftw.hometerview.databinding.BottomSheetTextListBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.parcelize.Parcelize

class TextListBottomSheet : BottomSheetDialogFragment() {
    companion object {
        private const val ARGUMENT_KEY = "TEXT_LIST_BOTTOM_SHEET_ARGUMENT_KEY"
        fun newInstance(items: List<String>): TextListBottomSheet {
            return TextListBottomSheet().apply {
                arguments = bundleOf(ARGUMENT_KEY to Argument(items))
            }
        }
    }

    interface Listener {
        fun onClickFromTextListBottomSheet(text: String)
    }

    private var _binding: BottomSheetTextListBinding? = null
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
            R.layout.bottom_sheet_text_list,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Argument>(ARGUMENT_KEY)?.also { argument ->
            initList(argument.items)
        } ?: dismissAllowingStateLoss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initList(items: List<String>) {
        binding.list.adapter = adapter
        adapter.submitList(
            items.map { text ->
                RecyclerItem(
                    data = Item(
                        text = text,
                        onClick = {
                            (activity as? Listener)?.onClickFromTextListBottomSheet(text)
                                ?: (parentFragment as? Listener)?.onClickFromTextListBottomSheet(text)
                            dismissAllowingStateLoss()
                        }
                    ),
                    layoutId = R.layout.list_item_bottom_sheet_text,
                    variableId = BR.item
                )
            }
        )
    }

    data class Item(
        val text: String,
        val onClick: () -> Unit
    ) {
        fun onClick() {
            onClick.invoke()
        }
    }

    @Parcelize
    data class Argument(
        val items: List<String>
    ) : Parcelable
}
