package com.ftw.hometerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class DataBindingRecyclerAdapter : ListAdapter<RecyclerItem, BindingViewHolder>(
    DiffCallback()
) {
    override fun getItemViewType(position: Int): Int {
        return getItem(position).layoutId
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, viewType, parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class BindingViewHolder(
    private val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: RecyclerItem) {
        val isVariableFound = binding.setVariable(item.variableId, item.data)
        if (isVariableFound.not()) {
            throw IllegalStateException(
                buildErrorMessage(item.variableId, binding)
            )
        }
        if (binding.hasPendingBindings()) {
            binding.executePendingBindings()
        }
    }
}

private fun buildErrorMessage(
    variableId: Int,
    binding: ViewDataBinding
): String {
    val variableName = DataBindingUtil.convertBrIdToString(variableId)
    val className = binding::class.simpleName
    return "Failed to find variable='$variableName' in the following databinding layout: $className"
}
