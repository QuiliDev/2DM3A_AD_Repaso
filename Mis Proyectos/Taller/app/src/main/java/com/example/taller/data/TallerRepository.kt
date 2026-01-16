package com.example.taller.data

import com.example.taller.data.dao.CocheDao
import com.example.taller.entity.Coche
import kotlinx.coroutines.flow.Flow

class TallerRepository(private val dao: CocheDao) {

    val todosLosCoches:Flow<List<Coche>> = dao.getAllCoches()

    // Insertar un Coche
    suspend fun insertarCoche(coche: Coche) {
        dao.insert(coche)
    }

    fun getCoche(id: Int): Flow<Coche> {
        return dao.getCoche(id)
    }

    // Borrar un Coche
    suspend fun eliminarCoche(coche: Coche) {
        dao.delete(coche)
    }

    // Actualizar un Coche existente
    suspend fun actualizarCoche(coche: Coche) {
        dao.update(coche)
    }

    fun filtrarCoches(titulo: String?): Flow<List<Coche>> =
        dao.filtrarCoche(titulo)
}