package com.github.maxapp.apinetwork

import com.github.maxapp.apinetwork.clientes.Clientes
import com.github.maxapp.apinetwork.pedidos.Pedidos
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.myjson.com/bins/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {

    @GET("1bo7qj")
    fun getClientes():
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Call<Clientes>

    @GET("wjl97")
    fun getPedidos():
            Call<Pedidos>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object ApiRetrofit {
    val RETROFIT_SERVICE : ApiService by lazy { retrofit.create(ApiService::class.java) }
}





