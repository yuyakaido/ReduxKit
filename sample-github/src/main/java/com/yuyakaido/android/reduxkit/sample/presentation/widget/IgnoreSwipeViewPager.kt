package com.yuyakaido.android.reduxkit.sample.presentation.widget

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class IgnoreSwipeViewPager @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null
) : ViewPager(context, attr) {

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

}