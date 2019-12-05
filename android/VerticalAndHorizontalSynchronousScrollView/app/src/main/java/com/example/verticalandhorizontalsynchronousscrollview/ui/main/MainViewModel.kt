package com.example.verticalandhorizontalsynchronousscrollview.ui.main

import android.view.MotionEvent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
  var maxScrollX = 0
  var maxScrollY = 0

  val scrollX = MutableLiveData<Int>()
  val scrollY = MutableLiveData<Int>()

  private lateinit var scrollType: ScrollType

  fun onScrollChanged(x: Int, y: Int) {
    when (scrollType) {
      ScrollType.HORIZONTAL -> {
        val percentage = x * 100 / maxScrollX
        scrollY.value = maxScrollY * percentage / 100
      }
      ScrollType.VERTICAL -> {
        val percentage = y * 100 / maxScrollY
        scrollX.value = maxScrollX * percentage / 100
      }
    }
  }

  fun onTouchScrollView(event: MotionEvent, type: ScrollType): Boolean {
    if (event.action == MotionEvent.ACTION_DOWN) {
      scrollType = type
    }
    return false
  }

  enum class ScrollType {
    HORIZONTAL,
    VERTICAL,
  }
}
