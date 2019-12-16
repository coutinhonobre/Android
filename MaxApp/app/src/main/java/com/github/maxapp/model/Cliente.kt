package com.github.maxapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clientes")
data class Cliente(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val codigo: String,
    @ColumnInfo(name = "razao_social")
    val razaoSocial: String,
    @ColumnInfo(name = "nome_fantasia")
    val nomeFantasia: String,
    val cnpj: String,
    @ColumnInfo(name = "ramo_atividade")
    val ramoAtividade: String,
    val endereco: String,
    val status: String
)