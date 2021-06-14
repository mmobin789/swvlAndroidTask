package pse.at.swivl.ui.utils

import android.widget.ImageView
import io.pixel.Pixel
import io.pixel.config.PixelOptions

import pse.at.swivl.R

/**
 * A convenient function to load images from internet on ImageView.
 * @see Pixel The library used which loads memory cached images.
 */
fun ImageView.load(url: String) {
    Pixel.load(
        url = url,
        imageView = this,
        pixelOptions = PixelOptions.Builder()
            .setPlaceholderResource(R.drawable.ic_loading_android).build()
    )

}