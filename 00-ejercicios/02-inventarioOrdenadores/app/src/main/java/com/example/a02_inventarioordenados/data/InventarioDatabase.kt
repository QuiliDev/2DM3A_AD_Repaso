package com.example.a02_inventarioordenados.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Ordenador::class], version = 1, exportSchema = false)
abstract class InventarioDatabase: RoomDatabase(){

    abstract fun ordenadorDao() : OrdenadorDAO


    companion object {
        @Volatile
        private var Instance: InventarioDatabase? = null

        fun getDatabase(context: Context): InventarioDatabase {
            //context.deleteDatabase("Inventario_database")
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context,
                    InventarioDatabase::class.java,
                    "Inventario_database"
                )
                    .build()
                    .also { Instance = it }
            }
        }

    }
}
