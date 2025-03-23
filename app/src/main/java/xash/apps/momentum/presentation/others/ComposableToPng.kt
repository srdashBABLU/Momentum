package xash.apps.momentum.presentation.others

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Environment
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import java.io.File
import java.io.FileOutputStream
import androidx.core.graphics.createBitmap

fun captureComposableToPNG(context: Context, composable: @Composable () -> Unit) {
    val composeView = ComposeView(context).apply {
        setContent { composable() }
    }

    // Measure and layout the view
    composeView.measure(
        View.MeasureSpec.makeMeasureSpec(1080, View.MeasureSpec.EXACTLY),
        View.MeasureSpec.makeMeasureSpec(1920, View.MeasureSpec.EXACTLY)
    )
    composeView.layout(0, 0, 1080, 1920)

    // Create Bitmap and Canvas
    val bitmap = createBitmap(1080, 1920)
    val canvas = Canvas(bitmap)
    composeView.draw(canvas)

    // Save Bitmap to PNG
    saveBitmapToPNG(context, bitmap)
}

fun saveBitmapToPNG(context: Context, bitmap: Bitmap) {
    val directory = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "ComposeScreenshots")
    if (!directory.exists()) {
        directory.mkdirs()
    }

    val file = File(directory, "screenshot_${System.currentTimeMillis()}.png")
    FileOutputStream(file).use { outputStream ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    }

    println("Image saved at: ${file.absolutePath}")
}
