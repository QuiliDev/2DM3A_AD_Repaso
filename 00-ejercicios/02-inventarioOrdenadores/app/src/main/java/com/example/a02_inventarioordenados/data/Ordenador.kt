package com.example.a02_inventarioordenados.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("ordenador")
data class Ordenador(
    @PrimaryKey(true)
    val id : Int = 0,
    val identificador : String = "PC-00",
    val modelo : String = "",
    val anio : Int = 0
)
