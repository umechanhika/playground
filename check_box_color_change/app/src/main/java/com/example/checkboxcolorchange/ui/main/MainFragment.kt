package com.example.checkboxcolorchange.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.checkboxcolorchange.databinding.MainFragmentBinding

class MainFragment : Fragment() {

  private lateinit var binding: MainFragmentBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View = MainFragmentBinding.inflate(inflater).also {
    it.lifecycleOwner = this
    binding = it
  }.root

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    binding.viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    binding.viewModel?.validated?.observe(this, Observer {
      Toast.makeText(context, "Validated!", Toast.LENGTH_SHORT).show()
    })
  }

  companion object {
    fun newInstance() = MainFragment()
  }
}