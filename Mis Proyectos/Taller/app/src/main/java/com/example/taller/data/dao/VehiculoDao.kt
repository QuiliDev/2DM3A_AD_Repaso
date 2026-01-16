package com.example.taller.data.dao

import androidx.room.*
import com.example.taller.data.entity.Vehiculo
import kotlinx.coroutines.flow.Flow

@Dao
interface VehiculoDao {
    @Query("SELECT * FROM vehiculos")
    fun getAllVehiculos(): Flow<List<Vehiculo>>

    // Obtener todos los vehículos de un cliente específico
    @Query("SELECT * FROM vehiculos WHERE id_cliente = :clienteId")
    fun getVehiculosByCliente(clienteId: Int): Flow<List<Vehiculo>>

    @Query("SELECT * FROM vehiculos WHERE matricula = :matricula")
    suspend fun getVehiculoByMatricula(matricula: String): Vehiculo?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vehiculo: Vehiculo)

    @Update
    suspend fun update(vehiculo: Vehiculo)

    @Delete
    suspend fun delete(vehiculo: Vehiculo)
}