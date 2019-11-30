package com.example.addscreenshottogallery.ui.util

import android.content.ContentResolver
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.core.view.drawToBitmap
import java.io.File
import java.io.FileOutputStream

object ScreenshotUtil {
  private const val DIRECTORY_NAME = "AddScreenshotToGallery"
  private const val FILE_EXTENSION = ".jpg"

  fun take(
    contentResolver: ContentResolver,
    targetView: View,
    fileName: String,
    completion: (Bitmap, Uri) -> Unit
  ) {
    val bitmap = targetView.drawToBitmap()
    targetView.draw(Canvas(bitmap))

    val contentValues = createContentValues(fileName)
    val uri: Uri

    if (Build.VERSION_CODES.Q <= Build.VERSION.SDK_INT) {
      contentValues.apply {
        put(
          MediaStore.Images.Media.RELATIVE_PATH,
          "${Environment.DIRECTORY_PICTURES}/$DIRECTORY_NAME"
        )
        put(MediaStore.MediaColumns.IS_PENDING, 1)
      }

      contentResolver.run {
        uri = insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
          ?: return

        openOutputStream(uri).use { bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it) }

        contentValues.put(MediaStore.MediaColumns.IS_PENDING, 0)
        update(uri, contentValues, null, null)
      }
    } else {
      val directory = File(
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
        DIRECTORY_NAME
      )
      if (!directory.exists()) directory.mkdirs()
      val file = File(directory, "$fileName$FILE_EXTENSION")

      FileOutputStream(file).use { bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it) }

      contentResolver.run {
        contentValues.put(MediaStore.Images.Media.DATA, file.absolutePath)
        uri = insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues) ?: return
      }
    }

    completion(bitmap, uri)
  }

  /**
   * [Build.VERSION_CODES.Q]以上とそれより前で設定できる値が違うため、ここでは共通部分のみ設定
   */
  private fun createContentValues(name: String) = ContentValues().apply {
    put(MediaStore.Images.Media.DISPLAY_NAME, "$name$FILE_EXTENSION")
    put(MediaStore.Images.Media.TITLE, name)
    put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
    put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1_000)
  }
}