package com.example.app.widgets

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout

@SuppressLint("ClickableViewAccessibility")
class RippleEffect @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val animationDuration = 1000L

    init {
        setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    animateAlpha(0.5f)
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    animateAlpha(1f)
                }
            }
            true
        }
    }

    private fun animateAlpha(targetAlpha: Float) {
        ObjectAnimator.ofFloat(this, "alpha", alpha, targetAlpha).apply {
            duration = animationDuration
            start()
        }
    }
}
