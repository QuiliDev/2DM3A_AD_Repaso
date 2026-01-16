package com.example.taller.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taller.data.dao.ClienteDao
import com.example.taller.data.dao.CocheDao
import com.example.taller.data.dao.ReparacionDao
import com.example.taller.data.dao.VehiculoDao
import com.example.taller.entity.Coche
import com.example.taller.data.entity.Cliente
import com.example.taller.data.entity.Vehiculo
import com.example.taller.data.entity.Reparacion

@Database(
    entities = [Coche::class, Cliente::class, Vehiculo::class, Reparacion::class],
    version = 2,
    exportSchema = false
)
abstract class TallerDatabase : RoomDatabase() {

    abstract fun cocheDao(): CocheDao
    abstract fun clienteDao(): ClienteDao
    abstract fun vehiculoDao(): VehiculoDao
    abstract fun reparacionDao(): ReparacionDao

    companion object {
        @Volatile
        private var Instance: TallerDatabase? = null

        fun getDatabase(context: Context): TallerDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    TallerDatabase::class.java,
                    "Taller_database"
                )
                    // Esto es vital porque he cambiado el esquema nuevo en esta parte 2
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}