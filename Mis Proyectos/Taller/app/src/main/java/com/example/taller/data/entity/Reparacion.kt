package com.example.taller.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
// Entidad Reparacion (Relacionada con Vehiculo)
@Entity(
    tableName = "reparaciones",
    foreignKeys = [
        ForeignKey(
            entity = Vehiculo::class,
            parentColumns = ["id"],
            childColumns = ["id_vehiculo"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Reparacion(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val id_vehiculo: Int, // FK
    val descripcion_servicio: String,
    val horas_trabajo: Double,
    val precio_mano_obra: Double
)
