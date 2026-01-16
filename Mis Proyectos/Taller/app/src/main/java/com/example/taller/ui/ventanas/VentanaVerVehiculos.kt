package com.example.taller.ui.ventanas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taller.ui.shared.CocheViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VentanaVerVehiculos(navController: NavController, modifier: Modifier, viewModel: CocheViewModel, idCliente: Int) {
    // Obtenemos solo los vehículos de este cliente
    val vehiculos by viewModel.getVehiculosPorCliente(idCliente).collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Vehículos del Cliente") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = modifier.padding(padding).padding(16.dp)) {
            if (vehiculos.isEmpty()) {
                Text("Este cliente aún no tiene vehículos registrados.")
            } else {
                LazyColumn {
                    items(vehiculos) { vehiculo ->
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                            elevation = CardDefaults.cardElevation(2.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(text = "${vehiculo.marca} ${vehiculo.modelo}", style = MaterialTheme.typography.titleMedium)
                                    Text(text = "Matrícula: ${vehiculo.matricula}")
                                    Text(text = "Año: ${vehiculo.año} | Color: ${vehiculo.color}", style = MaterialTheme.typography.bodySmall)
                                }
                                //REPARACIONES
                                IconButton(onClick = {
                                    navController.navigate("ver_reparaciones/${vehiculo.id}")
                                }) {
                                    Icon(Icons.Default.Build, contentDescription = "Ver Reparaciones")
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}