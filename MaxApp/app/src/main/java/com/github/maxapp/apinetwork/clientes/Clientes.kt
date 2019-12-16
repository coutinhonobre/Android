package com.github.maxapp.apinetwork.clientes

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Clientes (
    @Json(name = "cliente")
    val cliente: Cliente? = null
) : Parcelable