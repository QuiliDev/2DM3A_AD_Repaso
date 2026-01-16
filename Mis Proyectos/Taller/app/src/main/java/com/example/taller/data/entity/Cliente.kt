package com.example.taller.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// 1. Entidad Cliente
@Entity(tableName = "clientes")
data class Cliente(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val apellidos: String,
    val dni: String,
    val telefono: String,
    val email: String,
    val direccion: String
)