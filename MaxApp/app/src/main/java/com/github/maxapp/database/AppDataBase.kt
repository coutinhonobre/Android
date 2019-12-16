package com.github.maxapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.maxapp.model.Cliente
import com.github.maxapp.model.Contato
import com.github.maxapp.model.Pedido

@Database(entities = [Cliente::class, Contato::class, Pedido::class], version = 1)
abstract class AppDataBase(): RoomDatabase() {

    abstract fun Dao(): MaxAppDao

    companion object {
        var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDataBase::class.java,
                    "database.db"
                ).build()

                INSTANCE as AppDataBase
            }else{
                INSTANCE as AppDataBase
            }
        }
    }

}