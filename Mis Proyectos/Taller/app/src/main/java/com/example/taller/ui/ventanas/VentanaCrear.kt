package com.example.taller.ui.ventanas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taller.ui.components.ClienteForm
import com.example.taller.ui.shared.CocheViewModel

@Composable
fun VentanaCrear(navController: NavController, modifier: Modifier, viewModel: CocheViewModel) {
    ClienteForm(
        labelVentana = "Añadir Nuevo Cliente",
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
            viewModel.insertarCliente()
            navController.popBackStack()
        },
        onCancelarClick = { navController.popBackStack() }
    )
}