package com.ftw.hometerview.design

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.core.view.updatePadding

private const val DEFAULT_NUMBER = -1

class Button @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {
    private val drawableStartImageView: ImageView
    private val titleTextView: TextView
    private val numberTextView: TextView

    var icon: Drawable? = null
        set(value) {
            field = value
            drawableStartImageView.setImageDrawable(value)
        }

    var text: String? = null
        set(value) {
            field = value
            titleTextView.text = value
        }

    var number: Int? = null
        set(value) {
            field = value
            if (number != null && number != DEFAULT_NUMBER) {
                numberTextView.isVisible = true
                numberTextView.text = value.toString()
            } else {
                numberTextView.isVisible = false
            }
        }

    init {
        inflate(context, R.layout.view_button, this)
        drawableStartImageView = findViewById(R.id.drawable_start_image_view)
        titleTextView = findViewById(R.id.title_text_view)
        numberTextView = findViewById(R.id.number_text_view)

        setBackgroundResource(R.drawable.selector_bg_button)
        val horizontalPadding = resources.getDimensionPixelSize(R.dimen.button_horizontal_padding)
        val verticalPadding = resources.getDimensionPixelSize(R.dimen.button_vertical_padding)
        updatePadding(
            left = horizontalPadding,
            top = verticalPadding,
            right = horizontalPadding,
            bottom = verticalPadding
        )

        context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.Button,
            defStyle,
            0
        ).apply {
            try {
                isEnabled = getBoolean(R.styleable.Button_android_enabled, true)
                text = getString(R.styleable.Button_android_text)
                number = getInt(R.styleable.Button_number, DEFAULT_NUMBER)
                icon = getDrawable(R.styleable.Button_android_drawableStart)
            } catch (e: Exception) {
            } finally {
                recycle()
            }
        }
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        children.forEach { it.isEnabled = enabled }
    }
}
