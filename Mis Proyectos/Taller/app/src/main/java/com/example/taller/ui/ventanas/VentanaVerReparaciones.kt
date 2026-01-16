package com.example.taller.ui.ventanas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taller.ui.shared.CocheViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VentanaVerReparaciones(navController: NavController, modifier: Modifier, viewModel: CocheViewModel, idVehiculo: Int) {
    val reparaciones by viewModel.getReparacionesPorVehiculo(idVehiculo).collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Historial de Reparaciones") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("crear_reparacion/$idVehiculo") }) {
                Icon(Icons.Default.Add, contentDescription = "Añadir Reparación")
            }
        }
    ) { padding ->
        LazyColumn(modifier = modifier.padding(padding).padding(16.dp)) {
            items(reparaciones) { rep ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = rep.descripcion_servicio, style = MaterialTheme.typography.titleMedium)
                        Text(text = "Horas: ${rep.horas_trabajo} h | Precio/h: ${rep.precio_mano_obra}€")
                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                        val total = rep.horas_trabajo * rep.precio_mano_obra
                        Text(text = "TOTAL: ${String.format("%.2f", total)}€",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }
    }
}