package com.github.maxapp.apinetwork.clientes

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contato(
    val nome: String? = null,
    val telefone: String? = null,
    val celular: String? = null,
    val conjuge: String? = null,
    val tipo: String? = null,
    @Json(name = "e_mail") val eMail: String? = null,
    @Json(name = "data_nascimento") val dataNascimento: String? = null,
    val dataNascimentoConjuge: String? = null
) : Parcelable
