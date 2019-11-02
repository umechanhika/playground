package com.example.checkboxcolorchange.ui.extention

import android.widget.CompoundButton
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("buttonTint")
fun CompoundButton.setButtonTint(@ColorRes resId: Int) {
  buttonTintList = ContextCompat.getColorStateList(context, resId)
}