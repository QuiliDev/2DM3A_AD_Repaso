package com.example.taller.ui.ventanas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taller.ui.shared.CocheViewModel

@Composable
fun VentanaVer(navController: NavController, modifier: Modifier, viewModel: CocheViewModel) {
    val clientes by viewModel.todosLosClientes.collectAsState(initial = emptyList())

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Button(
            onClick = {
                viewModel.resetFormularios()
                navController.navigate("crear")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Añadir Cliente")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(clientes) { cliente ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "${cliente.nombre} ${cliente.apellidos}",
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(text = "DNI: ${cliente.dni} | Telf: ${cliente.telefono}")
                        }
                        Row {
                            // Botón para añadir un vehículo a este cliente
                            IconButton(onClick = {
                                viewModel.resetFormularios()
                                navController.navigate("crear_vehiculo/${cliente.id}")
                            }) {
                                Icon(Icons.Default.DirectionsCar, contentDescription = "Añadir Vehículo")
                            }

                            // Botón para editar los datos del cliente
                            IconButton(onClick = {
                                navController.navigate("editar/${cliente.id}")
                            }) {
                                Icon(Icons.Default.Edit, contentDescription = "Editar Cliente")
                            }

                            IconButton(onClick = {
                                navController.navigate("ver_vehiculos/${cliente.id}")
                            }) {
                                Icon(Icons.Default.Visibility, contentDescription = "Ver Vehículos")
                            }
                        }
                    }
                }
            }
        }
    }
}