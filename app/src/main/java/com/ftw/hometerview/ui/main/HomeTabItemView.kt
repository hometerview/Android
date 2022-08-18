package com.ftw.hometerview.ui.main

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import com.ftw.hometerview.R

private const val EXPANDED_HEIGHT_RES_ID = R.dimen.home_tab_layout_expanding_contents_height
private const val EXPANDED_TITLE_HEIGHT_RES_ID = R.dimen.home_tab_layout_expanding_title_height
private const val EXPANDED_CORNER_RADIUS_RES_ID = R.dimen.home_tab_layout_expanding_corner_radius
private const val COLLAPSED_HEIGHT_RES_ID = R.dimen.home_tab_layout_collapsed_contents_height
private const val COLLAPSED_TITLE_HEIGHT_RES_ID = R.dimen.home_tab_layout_collapsed_title_height
private const val COLLAPSED_RADIUS_RES_ID = R.dimen.home_tab_layout_collapsed_corner_radius
private const val BLUE_300_INT_COLOR_CODE = -0xb77f12
private const val BLUE_GRAY_200_INT_COLOR_CODE = -0x14110b

class HomeTabItemView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {

    private val titleTextView: TextView
    private val winnerBadgeTextView: TextView

    init {
        inflate(context, R.layout.view_home_tab_layout_item, this)
        titleTextView = findViewById(R.id.title_text_view)
        winnerBadgeTextView = findViewById(R.id.winner_badge_text_view)
    }

    var argument: Argument? = null
        set(value) {
            field = value
            if (value != null) {
                titleTextView.text = value.title
                winnerBadgeTextView.isVisible = value.showBadge
            }
        }

    fun updateBackground(collapsingRate: Float) {
        val isSelected = titleTextView.isSelected
        titleTextView.background = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            if (isSelected) intArrayOf(BLUE_300_INT_COLOR_CODE, BLUE_300_INT_COLOR_CODE)
            else intArrayOf(BLUE_GRAY_200_INT_COLOR_CODE, BLUE_GRAY_200_INT_COLOR_CODE)
        ).apply {
            cornerRadius =
                resources.getDimension(EXPANDED_CORNER_RADIUS_RES_ID) + resources.getDimension(
                COLLAPSED_RADIUS_RES_ID
            ) * collapsingRate
        }
    }

    fun updateCollapsedBackground() {
        this.updateLayoutParams<MarginLayoutParams> {
            height = resources.getDimensionPixelSize(COLLAPSED_HEIGHT_RES_ID)
        }
        winnerBadgeTextView.isVisible = false
        titleTextView.setTextColor(
            ContextCompat.getColorStateList(
                context,
                R.color.selector_building_review_collapsed_tab_text_color
            )
        )
        titleTextView.setBackgroundResource(R.drawable.selector_home_tab_item_background)
        titleTextView.updateLayoutParams<MarginLayoutParams> {
            topMargin = resources.getDimensionPixelSize(R.dimen.dp_size_4)
            height = resources.getDimensionPixelSize(COLLAPSED_TITLE_HEIGHT_RES_ID)
        }
    }

    fun updateExpandedBackground() {
        this.updateLayoutParams<MarginLayoutParams> {
            height = resources.getDimensionPixelSize(EXPANDED_HEIGHT_RES_ID)
        }
        winnerBadgeTextView.isVisible = argument?.showBadge ?: false
        titleTextView.setTextColor(
            ContextCompat.getColorStateList(
                context,
                R.color.selector_building_review_expanded_tab_text_color
            )
        )
        titleTextView.setBackgroundResource(R.drawable.selector_building_review_tab_background)
        titleTextView.updateLayoutParams<MarginLayoutParams> {
            topMargin = resources.getDimensionPixelSize(R.dimen.dp_size_6)
            height = resources.getDimensionPixelSize(EXPANDED_TITLE_HEIGHT_RES_ID)
        }
    }

    data class Argument(
        val title: String,
        val showBadge: Boolean
    )
}
