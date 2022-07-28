package com.ftw.hometerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ftw.hometerview.R

class AnimationAdapter(var guideImgaeList: List<Int>, var guideTextList: List<String>) : RecyclerView.Adapter<AnimationAdapter.AnimationViewHolder>() {

    inner class AnimationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageViewIcon = itemView.findViewById<ImageView>(R.id.imageView_icon)
        var guideText = itemView.findViewById<TextView>(R.id.guide_text)

        fun onBind(res: Int, text: String) {
            imageViewIcon.setImageResource(res)
            guideText.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimationViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_icon, parent, false)
        return AnimationViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimationViewHolder, position: Int) {
        holder.onBind(guideImgaeList[position], guideTextList[position])
    }

    override fun getItemCount(): Int = guideImgaeList.size

}