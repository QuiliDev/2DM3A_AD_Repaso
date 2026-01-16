package com.example.taller.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

// Entidad Vehiculo (Relacionada con Cliente)
@Entity(
    tableName = "vehiculos",
    foreignKeys = [
        ForeignKey(
            entity = Cliente::class,
            parentColumns = ["id"],
            childColumns = ["id_cliente"],
            onDelete = ForeignKey.CASCADE // Cascade porque si borras al cliente, se borran sus coches
        )
    ]
)
data class Vehiculo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val id_cliente: Int, // FK
    val matricula: String,
    val marca: String,
    val modelo: String,
    val año: Int,
    val color: String
)