package net.csolorzano.clase5.model

import com.squareup.moshi.Json

data class CatBreedData(
    @field:Json(name="name") val nombre: String,
    @field:Json(name="temperament") val temperamento: String
)
