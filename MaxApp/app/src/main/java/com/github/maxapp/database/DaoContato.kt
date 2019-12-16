package com.github.maxapp.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.*
import com.github.maxapp.model.Contato

@Dao
interface DaoContato {

    @Query("select * from contatos")
    fun getAllContato(): List<Contato>

    @Query("select * from contatos")
    fun getAllLiveContato(): LiveData<List<Contato>>

    @Query("select * from contatos where nome = :nome and client_id = :cliente")
    fun getAllContatoClienteNome(nome: String, cliente: Long): List<Contato>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSingleContato(contato: Contato)

    @Update
    fun updateContato(contato: Contato)

}
