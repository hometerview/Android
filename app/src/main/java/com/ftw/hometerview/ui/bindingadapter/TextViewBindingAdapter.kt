package com.ftw.hometerview.ui.bindingadapter

import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

@BindingAdapter("android:text")
fun setText(textView: TextView, text: String?) {
    textView.text = text
}

@InverseBindingAdapter(attribute = "android:text", event = "textViewTextAttrChanged")
fun getText(textView: TextView): String {
    return textView.text?.toString() ?: ""
}

@BindingAdapter("textViewTextAttrChanged")
fun setTextViewAttrChangedListener(textView: TextView, attrChange: InverseBindingListener) {
    textView.doAfterTextChanged { attrChange.onChange() }
}
