package com.example.taller.data.dao

import androidx.room.*
import com.example.taller.data.entity.Cliente
import kotlinx.coroutines.flow.Flow

@Dao
interface ClienteDao {
    @Query("SELECT * FROM clientes ORDER BY apellidos ASC")
    fun getAllClientes(): Flow<List<Cliente>>

    @Query("SELECT * FROM clientes WHERE id = :id")
    fun getClienteById(id: Int): Flow<Cliente>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cliente: Cliente)

    @Update
    suspend fun update(cliente: Cliente)

    @Delete
    suspend fun delete(cliente: Cliente)

    @Query("SELECT * FROM clientes WHERE nombre LIKE '%' || :search || '%' OR dni LIKE '%' || :search || '%'")
    fun buscarClientes(search: String): Flow<List<Cliente>>
}