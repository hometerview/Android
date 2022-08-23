package com.ftw.hometerview.design

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.updatePadding

private const val DEFAULT_ALIGNMENT = TEXT_ALIGNMENT_CENTER
private const val DEFAULT_ELEVATION = 10.0f
private const val DEFAULT_ENABLED = true

@RequiresApi(Build.VERSION_CODES.M)
class MainButton @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {
    private val titleTextView: TextView

    var text: String? = null
        set(value) {
            field = value
            titleTextView.text = value
        }

    init {
        inflate(context, R.layout.view_main_button, this)
        titleTextView = findViewById(R.id.title_text_view)

        val horizontalPadding = resources.getDimensionPixelSize(R.dimen.button_horizontal_padding)
        val verticalPadding = resources.getDimensionPixelSize(R.dimen.main_button_vertical_padding)
        updatePadding(
            left = horizontalPadding,
            top = verticalPadding,
            right = horizontalPadding,
            bottom = verticalPadding
        )

        context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.MainButton,
            defStyle,
            0
        ).apply {
            try {
                text = getString(R.styleable.MainButton_android_text)
                titleTextView.textAlignment = getInt(R.styleable.MainButton_android_textAlignment, DEFAULT_ALIGNMENT)
                elevation = getFloat(R.styleable.MainButton_android_elevation, DEFAULT_ELEVATION)
                isEnabled = getBoolean(R.styleable.MainButton_android_enabled, DEFAULT_ENABLED)
            } catch (e: Exception) {
            } finally {
                recycle()
            }
        }
    }

    override fun setElevation(elevation: Float) {
        super.setElevation(elevation)
        children.forEach { it.elevation = elevation }
    }

    override fun setTextAlignment(textAlignment: Int) {
        super.setTextAlignment(textAlignment)
        children.forEach { it.textAlignment = textAlignment }
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        if(enabled){
            setBackgroundResource(R.drawable.button_main_button)
        } else{
            setBackgroundResource(R.drawable.bg_button_disabled)
        }
    }
}
