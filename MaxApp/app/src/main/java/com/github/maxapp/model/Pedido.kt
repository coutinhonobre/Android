package com.github.maxapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pedidos")
data class Pedido(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "numero_ped_rca")
    val numeroPedRca: String,
    @ColumnInfo(name = "codigo_cliente")
    val codigoCliente: String,
    @ColumnInfo(name = "nome_cliente")
    val nomeCliente: String,
    val status: String,
    val critica: String,
    val tipo: String,
    val legendas: String
)
