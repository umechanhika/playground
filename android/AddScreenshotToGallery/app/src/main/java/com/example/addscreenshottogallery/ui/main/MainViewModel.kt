package com.example.addscreenshottogallery.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

  val takeScreenshot = MutableLiveData<Unit>()

  fun onClickScreenshot() {
    takeScreenshot.value = Unit
  }
}
