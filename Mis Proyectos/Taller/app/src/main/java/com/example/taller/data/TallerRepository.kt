package com.example.taller.data

import com.example.taller.data.dao.ClienteDao
import com.example.taller.data.dao.ReparacionDao
import com.example.taller.data.dao.VehiculoDao
import com.example.taller.data.entity.Cliente
import com.example.taller.data.entity.Reparacion
import com.example.taller.data.entity.Vehiculo
import kotlinx.coroutines.flow.Flow

class TallerRepository(
    private val clienteDao: ClienteDao,
    private val vehiculoDao: VehiculoDao,
    private val reparacionDao: ReparacionDao
) {

    // --- CLIENTES ---
    val todosLosClientes: Flow<List<Cliente>> = clienteDao.getAllClientes()

    // Función que faltaba para la edición
    fun getClienteById(id: Int): Flow<Cliente?> = clienteDao.getClienteById(id)

    suspend fun insertarCliente(cliente: Cliente) = clienteDao.insert(cliente)
    suspend fun actualizarCliente(cliente: Cliente) = clienteDao.update(cliente)
    suspend fun eliminarCliente(cliente: Cliente) = clienteDao.delete(cliente)

    // --- VEHÍCULOS ---
    val todosLosVehiculos: Flow<List<Vehiculo>> = vehiculoDao.getAllVehiculos()

    fun getVehiculosDeCliente(clienteId: Int): Flow<List<Vehiculo>> =
        vehiculoDao.getVehiculosByCliente(clienteId)

    suspend fun insertarVehiculo(vehiculo: Vehiculo) = vehiculoDao.insert(vehiculo)

    // --- REPARACIONES ---
    fun getReparacionesDeVehiculo(vehiculoId: Int): Flow<List<Reparacion>> =
        reparacionDao.getReparacionesByVehiculo(vehiculoId)

    suspend fun insertarReparacion(reparacion: Reparacion) = reparacionDao.insert(reparacion)
}