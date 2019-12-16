package com.github.maxapp.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.*
import com.github.maxapp.model.Cliente

@Dao
interface DaoClientes {

    @Query("select * from clientes")
    fun getAllCliente(): List<Cliente>

    @Query("select * from clientes")
    fun getAllLiveCliente(): LiveData<List<Cliente>>

    @Query("select * from clientes where codigo = :codigo")
    fun getAllClienteID(codigo: String): List<Cliente>


    @Insert
    fun addSingleCliente(cliente: Cliente)

    @Update
    fun updateCliente(cliente: Cliente)

}
