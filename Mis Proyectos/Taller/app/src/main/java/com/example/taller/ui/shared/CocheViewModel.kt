package com.example.taller.ui.shared

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taller.data.TallerRepository
import com.example.taller.data.entity.Cliente
import com.example.taller.data.entity.Vehiculo
import com.example.taller.data.entity.Reparacion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CocheViewModel(
    private val repositorio: TallerRepository
) : ViewModel() {

    // ESTADOS FORMULARIO CLIENTE
    var nombreCliente by mutableStateOf("")
    var apellidosCliente by mutableStateOf("")
    var dniCliente by mutableStateOf("")
    var telefonoCliente by mutableStateOf("")
    var emailCliente by mutableStateOf("")
    var direccionCliente by mutableStateOf("")

    // ESTADOS FORMULARIO VEHICULO
    var matriculaVehiculo by mutableStateOf("")
    var marcaVehiculo by mutableStateOf("")
    var modeloVehiculo by mutableStateOf("")
    var anioVehiculo by mutableStateOf("")
    var colorVehiculo by mutableStateOf("")
    var idClienteSeleccionado by mutableStateOf(0)

    // ESTADOS FORMULARIO REPARACION
    var descripcionServicio by mutableStateOf("")
    var horasTrabajo by mutableStateOf("")
    var precioManoObra by mutableStateOf("")

    // CONSULTAS FLOW
    val todosLosClientes: Flow<List<Cliente>> = repositorio.todosLosClientes
    val todosLosVehiculos: Flow<List<Vehiculo>> = repositorio.todosLosVehiculos

    fun getVehiculosPorCliente(clienteId: Int): Flow<List<Vehiculo>> =
        repositorio.getVehiculosDeCliente(clienteId)

    fun getReparacionesPorVehiculo(vehiculoId: Int): Flow<List<Reparacion>> =
        repositorio.getReparacionesDeVehiculo(vehiculoId)

    // OPERACIONES CLIENTE
    fun insertarCliente() {
        val nuevo = Cliente(
            nombre = nombreCliente, apellidos = apellidosCliente, dni = dniCliente,
            telefono = telefonoCliente, email = emailCliente, direccion = direccionCliente
        )
        viewModelScope.launch(Dispatchers.IO) { repositorio.insertarCliente(nuevo) }
    }

    // OPERACIONES VEHICULO
    fun insertarVehiculo() {
        val nuevo = Vehiculo(
            id_cliente = idClienteSeleccionado, matricula = matriculaVehiculo,
            marca = marcaVehiculo, modelo = modeloVehiculo,
            año = anioVehiculo.toIntOrNull() ?: 0, color = colorVehiculo
        )
        viewModelScope.launch(Dispatchers.IO) { repositorio.insertarVehiculo(nuevo) }
    }

    // OPERACIONES REPARACION
    fun insertarReparacion(idVehiculo: Int) {
        val nueva = Reparacion(
            id_vehiculo = idVehiculo,
            descripcion_servicio = descripcionServicio,
            horas_trabajo = horasTrabajo.toDoubleOrNull() ?: 0.0,
            precio_mano_obra = precioManoObra.toDoubleOrNull() ?: 0.0
        )
        viewModelScope.launch(Dispatchers.IO) { repositorio.insertarReparacion(nueva) }
    }

    // LIMPIEZA
    fun resetFormularios() {
        nombreCliente = ""; apellidosCliente = ""; dniCliente = ""
        matriculaVehiculo = ""; marcaVehiculo = ""; modeloVehiculo = ""
        descripcionServicio = ""; horasTrabajo = ""; precioManoObra = ""
    }

    fun getClientePorId(id: Int): Flow<Cliente?> = repositorio.getClienteById(id)

    fun actualizarCliente(id: Int) {
        val clienteEditado = Cliente(
            id = id,
            nombre = nombreCliente,
            apellidos = apellidosCliente,
            dni = dniCliente,
            telefono = telefonoCliente,
            email = emailCliente,
            direccion = direccionCliente
        )
        viewModelScope.launch(Dispatchers.IO) {
            repositorio.actualizarCliente(clienteEditado)
        }
    }
}
