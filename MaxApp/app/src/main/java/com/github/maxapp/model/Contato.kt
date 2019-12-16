package com.github.maxapp.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE


@Entity(tableName = "contatos")
data class Contato(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nome: String,
    val telefone: String,
    val celular: String,
    val conjuge: String,
    val tipo: String,
    @ColumnInfo(name = "email")
    val eMail: String,
    @ColumnInfo(name = "data_nascimento")
    val dataNascimento: String,
    @ColumnInfo(name = "data_nascimento_conjugue")
    val dataNascimentoConjuge: String,
    @ColumnInfo(name = "client_id")
    val clienteId: Long
)
