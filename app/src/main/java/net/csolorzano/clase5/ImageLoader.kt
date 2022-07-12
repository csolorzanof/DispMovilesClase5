package net.csolorzano.clase5

import android.widget.ImageView

interface ImageLoader {
    fun cargarImagen(urlImagen: String, imageView: ImageView)
}