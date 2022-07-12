package net.csolorzano.clase5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import net.csolorzano.clase5.api.TheCatApiService
import net.csolorzano.clase5.model.ImageResultData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {
    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    private val theCatApiService by lazy{
        retrofit.create(TheCatApiService::class.java)
    }

    private val txtRespuesta:TextView by lazy { findViewById(R.id.txtRespuesta) }
    private val imvFoto:ImageView by lazy { findViewById(R.id.imvFoto) }
    private val imageLoader: ImageLoader by lazy { GlideImageLoader(this) }

    private fun getRespuestaImagenGato(){
        val call = theCatApiService.buscarImagenes(10, "full")
        call.enqueue(object : Callback<List<ImageResultData>>{
            override fun onResponse(call: Call<List<ImageResultData>>, response: Response<List<ImageResultData>>) {
                if(response.isSuccessful){
                    val resultados = response.body()
                    val primeraImagen = resultados?.firstOrNull()?.urlImagen ?: ""
                    txtRespuesta.text = "URL de Imagen: $primeraImagen"
                    if(primeraImagen.isNotBlank()){
                        imageLoader.cargarImagen(primeraImagen, imvFoto)
                    }else{
                        Log.d("Main Activity", "Falta URL de imagen")
                    }
                }else{
                    Log.e("MainActivity",
                        "Falla al obtener resultados \n${response.errorBody()?.string() ?: "" }")
                }
            }

            override fun onFailure(call: Call<List<ImageResultData>>, t: Throwable) {
                Log.e("MainActivity", "Falla al obtener resultados", t)
            }

        } )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getRespuestaImagenGato()
    }
}