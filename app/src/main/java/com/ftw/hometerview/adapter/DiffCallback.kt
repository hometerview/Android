package com.ftw.hometerview.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

internal class DiffCallback : DiffUtil.ItemCallback<RecyclerItem>() {

    override fun areItemsTheSame(
        oldItem: RecyclerItem,
        newItem: RecyclerItem
    ): Boolean {
        val oldData = oldItem.data
        val newData = newItem.data
        return oldData == newData
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: RecyclerItem,
        newItem: RecyclerItem
    ): Boolean {
        val oldData = oldItem.data
        val newData = newItem.data
        return oldData == newData
    }
}