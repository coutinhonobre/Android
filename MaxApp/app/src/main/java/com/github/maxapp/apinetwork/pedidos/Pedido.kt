package com.github.maxapp.apinetwork.pedidos

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pedido(
    @Json(name = "numero_ped_Rca")
    val numeroPedRca: String? = null,
    val codigoCliente: String? = null,
    @Json(name = "NOMECLIENTE")
    val nomeCliente: String? = null,
    val data: String? = null,
    val status: String? = null,
    val critica: String? = null,
    val tipo: String? = null,
    val legendas: List<String>? = null
): Parcelable



