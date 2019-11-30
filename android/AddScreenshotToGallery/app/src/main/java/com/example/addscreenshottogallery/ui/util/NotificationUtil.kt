package com.example.addscreenshottogallery.ui.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.addscreenshottogallery.R

object NotificationUtil {
  private const val CHANNEL_ID = "screenshot"

  fun createNotificationChannel(context: Context) {
    if (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT) {
      val name = context.getString(R.string.channel_name_screenshot)
      val descriptionText = context.getString(R.string.channel_description_screenshot)
      val importance = NotificationManager.IMPORTANCE_DEFAULT
      val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
        description = descriptionText
      }
      val notificationManager: NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
      notificationManager.createNotificationChannel(channel)
    }
  }

  fun notify(context: Context, bitmap: Bitmap, uri: Uri) {
    val notification = NotificationCompat.Builder(context, CHANNEL_ID)
      .setContentText(context.getString(R.string.complete_saving_screenshot))
      .setSmallIcon(R.drawable.ic_launcher_foreground)
      .setVibrate(longArrayOf(0, 100, 100, 100))
      .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap))
      .setContentIntent(PendingIntent.getActivity(context, 0, Intent(Intent.ACTION_VIEW, uri), 0))
      .build()
    NotificationManagerCompat.from(context).notify(0, notification)
  }
}