package net.csolorzano.clase5.model

import com.squareup.moshi.Json

data class ImageResultData(
    @field:Json(name="url") val urlImagen: String,
    @field:Json(name="breeds") val razas: List<CatBreedData>
)
