package net.csolorzano.clase5

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader(private val context: Context) : ImageLoader {
    override fun cargarImagen(urlImagen: String, imageView: ImageView) {
        Glide.with(context)
            .load(urlImagen)
            .centerCrop()
            .into(imageView)
    }
}