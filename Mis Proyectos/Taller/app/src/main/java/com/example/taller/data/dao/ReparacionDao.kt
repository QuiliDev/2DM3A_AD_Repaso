package com.example.taller.data.dao

import androidx.room.*
import com.example.taller.data.entity.Reparacion
import kotlinx.coroutines.flow.Flow

@Dao
interface ReparacionDao {
    @Query("SELECT * FROM reparaciones")
    fun getAllReparaciones(): Flow<List<Reparacion>>

    // Obtener historial de reparaciones de un vehículo concreto
    @Query("SELECT * FROM reparaciones WHERE id_vehiculo = :vehiculoId")
    fun getReparacionesByVehiculo(vehiculoId: Int): Flow<List<Reparacion>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reparacion: Reparacion)

    @Update
    suspend fun update(reparacion: Reparacion)

    @Delete
    suspend fun delete(reparacion: Reparacion)

    // Consulta útil: Calcular el coste total de una reparación (Mano de obra * horas)
    // Esto se puede hacer en el ViewModel, pero aquí tienes el acceso a datos
}