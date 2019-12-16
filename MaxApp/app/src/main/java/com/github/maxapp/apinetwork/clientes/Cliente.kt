package com.github.maxapp.apinetwork.clientes

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cliente(
    val id: Long? = null,
    val codigo: String? = null,
    @Json(name = "razao_social") val razaoSocial: String? = null,
    val nomeFantasia: String? = null,
    val cnpj: String? = null,
    @Json(name = "ramo_atividade") val ramoAtividade: String? = null,
    val endereco: String? = null,
    val status: String? = null,
    val contatos: List<Contato>? = null
) : Parcelable