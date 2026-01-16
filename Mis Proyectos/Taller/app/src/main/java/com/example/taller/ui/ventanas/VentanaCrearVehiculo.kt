package com.example.taller.ui.ventanas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taller.ui.shared.CocheViewModel

@Composable
fun VentanaCrearVehiculo(navController: NavController, modifier: Modifier, viewModel: CocheViewModel, idCliente: Int) {

    // Asignamos el ID del cliente al estado del ViewModel para la relación FK
    viewModel.idClienteSeleccionado = idCliente

    Column(modifier = modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Registrar Vehículo", style = MaterialTheme.typography.headlineMedium)
        Text("Cliente ID: $idCliente", style = MaterialTheme.typography.bodySmall)

        OutlinedTextField(value = viewModel.matriculaVehiculo, onValueChange = { viewModel.matriculaVehiculo = it }, label = { Text("Matrícula") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = viewModel.marcaVehiculo, onValueChange = { viewModel.marcaVehiculo = it }, label = { Text("Marca") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = viewModel.modeloVehiculo, onValueChange = { viewModel.modeloVehiculo = it }, label = { Text("Modelo") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = viewModel.anioVehiculo, onValueChange = { viewModel.anioVehiculo = it }, label = { Text("Año") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = viewModel.colorVehiculo, onValueChange = { viewModel.colorVehiculo = it }, label = { Text("Color") }, modifier = Modifier.fillMaxWidth())

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { navController.popBackStack() }) { Text("Cancelar") }
            Button(onClick = {
                viewModel.insertarVehiculo()
                navController.popBackStack()
            }) { Text("Guardar Vehículo") }
        }
    }
}