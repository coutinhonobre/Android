package com.github.maxapp.apinetwork.pedidos

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pedidos (
    @Json(name = "pedidos")
    val pedidos: List<Pedido>? = null
) : Parcelable