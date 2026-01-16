package com.example.taller.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coche")
data class Coche(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val price: Double,
    val quantity: Int
)