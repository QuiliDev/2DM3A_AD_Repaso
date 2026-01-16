package com.example.taller.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.taller.entity.Coche
import kotlinx.coroutines.flow.Flow

@Dao
interface CocheDao {

    @Query("SELECT * from coche ORDER BY name ASC")
    fun getAllCoches(): Flow<List<Coche>>

    @Query("SELECT * from coche WHERE id = :id")
    fun getCoche(id: Int): Flow<Coche>

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend fun insert(entity: Coche)

    @Update()
    suspend fun update(entity: Coche)

    @Delete
    suspend fun delete(entity: Coche)

    @Query("""
        SELECT * FROM Coche
        WHERE (:titulo IS NULL OR name LIKE '%' || :titulo || '%')
    """)
    fun filtrarCoche(titulo: String?): Flow<List<Coche>>
}