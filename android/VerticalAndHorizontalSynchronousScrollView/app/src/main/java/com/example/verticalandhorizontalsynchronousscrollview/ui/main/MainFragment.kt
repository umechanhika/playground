package com.example.verticalandhorizontalsynchronousscrollview.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.verticalandhorizontalsynchronousscrollview.databinding.MainFragmentBinding

class MainFragment : Fragment() {

  private lateinit var binding: MainFragmentBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return MainFragmentBinding.inflate(inflater).apply {
      binding = this
      binding.lifecycleOwner = viewLifecycleOwner

      horizontalScrollView.doOnLayout {
        it as ViewGroup
        binding.viewModel?.maxScrollX = it.children.last().right - it.width
      }

      verticalScrollView.doOnLayout {
        it as ViewGroup
        binding.viewModel?.maxScrollY = it.children.last().bottom - it.height
      }
    }.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    binding.viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
  }

  companion object {
    fun newInstance() = MainFragment()
  }
}
