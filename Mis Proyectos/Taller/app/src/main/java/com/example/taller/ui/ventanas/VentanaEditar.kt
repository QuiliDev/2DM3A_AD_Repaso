package com.example.taller.ui.ventanas

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.taller.ui.components.ClienteForm
import com.example.taller.ui.shared.CocheViewModel
import kotlinx.coroutines.launch

@Composable
fun VentanaEditar(
    navController: NavController,
    modifier: Modifier,
    viewModel: CocheViewModel,
    id: Int
) {
    val scope = rememberCoroutineScope()

    // CARGA DE DATOS AL INICIAR
    LaunchedEffect(id) {
        viewModel.getClientePorId(id).collect { cliente ->
            cliente?.let {
                viewModel.nombreCliente = it.nombre
                viewModel.apellidosCliente = it.apellidos
                viewModel.dniCliente = it.dni
                viewModel.telefonoCliente = it.telefono
                viewModel.emailCliente = it.email
                viewModel.direccionCliente = it.direccion
            }
        }
    }

    // REUTILIZACIÓN DEL FORMULARIO
    ClienteForm(
        labelVentana = "Editar Datos del Cliente",
        nombre = viewModel.nombreCliente,
        apellidos = viewModel.apellidosCliente,
        dni = viewModel.dniCliente,
        telefono = viewModel.telefonoCliente,
        email = viewModel.emailCliente,
        direccion = viewModel.direccionCliente,
        onNombreChange = { viewModel.nombreCliente = it },
        onApellidosChange = { viewModel.apellidosCliente = it },
        onDniChange = { viewModel.dniCliente = it },
        onTelefonoChange = { viewModel.telefonoCliente = it },
        onEmailChange = { viewModel.emailCliente = it },
        onDireccionChange = { viewModel.direccionCliente = it },
        onAceptarClick = {
            scope.launch {
                viewModel.actualizarCliente(id)
                navController.popBackStack()
            }
        },
        onCancelarClick = {
            navController.popBackStack()
        }
    )
}