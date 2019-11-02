package com.example.checkboxcolorchange.ui.main

import androidx.annotation.IdRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.checkboxcolorchange.R

class MainViewModel : ViewModel() {
  val validated = MutableLiveData<Unit>()

  val isCheckedCheckBox1 = MutableLiveData<Boolean>(false)
  val isCheckedCheckBox2 = MutableLiveData<Boolean>(false)

  val checkBox1Color = MutableLiveData<Int>(R.color.check_box_color_valid)
  val checkBox2Color = MutableLiveData<Int>(R.color.check_box_color_valid)

  fun onCheckedChanged(@IdRes resId: Int) {
    checkBox1Color.value = R.color.check_box_color_valid
    checkBox2Color.value = R.color.check_box_color_valid

    when (resId) {
      R.id.checkBox1 -> isCheckedCheckBox1.value = isCheckedCheckBox1.value?.not()
      R.id.checkBox2 -> isCheckedCheckBox2.value = isCheckedCheckBox2.value?.not()
    }
  }

  fun onClickValidateButton() {
    if (!isValid()) {
      checkBox1Color.value = R.color.check_box_color_invalid
      checkBox2Color.value = R.color.check_box_color_invalid
      return
    }
    validated.value = Unit
  }

  private fun isValid() = isCheckedCheckBox1.value == true && isCheckedCheckBox2.value == true
}
