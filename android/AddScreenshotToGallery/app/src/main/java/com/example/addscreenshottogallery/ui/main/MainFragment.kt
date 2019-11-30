package com.example.addscreenshottogallery.ui.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.example.addscreenshottogallery.R
import com.example.addscreenshottogallery.databinding.MainFragmentBinding
import com.example.addscreenshottogallery.ui.util.NotificationUtil
import com.example.addscreenshottogallery.ui.util.ScreenshotUtil
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainFragment : Fragment() {

  private lateinit var binding: MainFragmentBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View = MainFragmentBinding.inflate(inflater).apply {
    binding = this
    lifecycleOwner = viewLifecycleOwner
  }.root

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    binding.viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java).apply {
      takeScreenshot.observe(viewLifecycleOwner) {
        if (checkPermission(requireContext())) {
          takeScreenshot()
        } else {
          requestPermissions(
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            REQUEST_CODE_WRITE_EXTERNAL_STORAGE
          )
        }
      }
    }
  }

  override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
  ) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    when (requestCode) {
      REQUEST_CODE_WRITE_EXTERNAL_STORAGE -> {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          takeScreenshot()
        } else {
          Toast.makeText(context, R.string.permission_denied, Toast.LENGTH_LONG).show()
        }
      }
    }
  }

  /**
   * [Build.VERSION_CODES.Q]以上は共有メディアファイルの書き込みに権限は不要(https://developer.android.com/training/data-storage)
   */
  private fun checkPermission(context: Context): Boolean =
    Build.VERSION_CODES.Q <= Build.VERSION.SDK_INT ||
            ContextCompat.checkSelfPermission(
              context,
              Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED

  private fun takeScreenshot() {
    ScreenshotUtil.take(
      contentResolver = activity?.contentResolver ?: return,
      targetView = binding.card,
      fileName = "Screenshot_${SimpleDateFormat(
        "yyyyMMdd_HHmmss",
        Locale.getDefault()
      ).format(Date())}",
      completion = { bitmap, uri ->
        NotificationUtil.notify(requireContext(), bitmap, uri)
      }
    )
  }

  companion object {
    private const val REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 1_000

    fun newInstance() = MainFragment()
  }
}
