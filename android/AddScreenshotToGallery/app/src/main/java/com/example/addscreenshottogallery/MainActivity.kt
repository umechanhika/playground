package com.example.addscreenshottogallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.addscreenshottogallery.ui.main.MainFragment
import com.example.addscreenshottogallery.ui.util.NotificationUtil

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
        .replace(R.id.container, MainFragment.newInstance())
        .commitNow()
    }

    NotificationUtil.createNotificationChannel(this)
  }

}
