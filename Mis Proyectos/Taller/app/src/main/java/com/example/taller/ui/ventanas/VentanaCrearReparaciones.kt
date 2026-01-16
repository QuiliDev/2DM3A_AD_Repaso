package com.example.taller.ui.ventanas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taller.ui.shared.CocheViewModel

@Composable
fun VentanaCrearReparacion(navController: NavController, modifier: Modifier, viewModel: CocheViewModel, idVehiculo: Int) {
    Column(modifier = modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Nueva Reparación", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(value = viewModel.descripcionServicio, onValueChange = { viewModel.descripcionServicio = it }, label = { Text("Descripción (Aceite, Frenos...)") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = viewModel.horasTrabajo, onValueChange = { viewModel.horasTrabajo = it }, label = { Text("Horas de Trabajo") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = viewModel.precioManoObra, onValueChange = { viewModel.precioManoObra = it }, label = { Text("Precio Mano de Obra (€/h)") }, modifier = Modifier.fillMaxWidth())

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { navController.popBackStack() }) { Text("Cancelar") }
            Button(onClick = {
                viewModel.insertarReparacion(idVehiculo)
                navController.popBackStack()
            }) { Text("Guardar Registro") }
        }
    }
}