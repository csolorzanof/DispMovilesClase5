package net.csolorzano.clase5.api

import net.csolorzano.clase5.model.ImageResultData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface TheCatApiService {
    @GET("images/search")
    fun buscarImagenes(
        @Query("limit") limite: Int,
        @Query("format") formato: String
    ) : Call<List<ImageResultData>>
}