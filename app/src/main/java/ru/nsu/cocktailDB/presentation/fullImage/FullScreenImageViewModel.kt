package ru.nsu.cocktailDB.presentation.fullImage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModel
import androidx.palette.graphics.Palette
import ru.nsu.cocktailDB.R

class FullScreenImageViewModel(private val context: Context) : ViewModel() {
    fun getDominantImageColor(drawable: Drawable): Int? {
        var color = context.getColor(R.color.colorCardBackground)
        Palette.Builder(drawableToBitmap(drawable)).generate { palette ->
            palette?.let {
                color = it.getDominantColor(color)
            }
        }
        return color
    }

    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable && drawable.bitmap != null) {
            return drawable.bitmap
        }
        val bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
            Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888) // Single color bitmap will be created of 1x1 pixel
        } else {
            Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        }
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}