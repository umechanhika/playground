package com.example.verticalandhorizontalsynchronousscrollview.ui.extension

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("onTouch")
fun View.setOnTouch(l: View.OnTouchListener) = setOnTouchListener(l)

@BindingAdapter("onScrollChange")
fun View.setOnScrollChange(l: View.OnScrollChangeListener) = setOnScrollChangeListener(l)