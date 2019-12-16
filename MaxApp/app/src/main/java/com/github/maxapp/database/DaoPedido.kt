package com.github.maxapp.database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.github.maxapp.model.Pedido

interface DaoPedido {

    @Query("select * from pedidos")
    fun getAllPedidos(): List<Pedido>

    @Query("select * from pedidos")
    fun getAllLivePedido(): LiveData<List<Pedido>>

    @Query("select * from pedidos where numero_ped_rca = :numero_ped_rca")
    fun getAllPedidoRca(numero_ped_rca: String): List<Pedido>

    @Query("select * from pedidos where numero_ped_rca = :numero_ped_rca")
    fun getAllPedidoRcaLive(numero_ped_rca: String): LiveData<List<Pedido>>


    @Insert
    fun addSinglePedido(pedido: Pedido)

    @Update
    fun updatePedido(pedido: Pedido)

}
