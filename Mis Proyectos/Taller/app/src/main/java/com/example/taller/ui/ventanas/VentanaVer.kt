package com.example.taller.ui.ventanas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taller.entity.Coche
import com.example.taller.ui.shared.CocheViewModel
import kotlinx.coroutines.launch

@Composable
fun VentanaVer(navController: NavController, modifier: Modifier, viewModel: CocheViewModel) {
    val coches by viewModel.allCoches.collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()
    var cocheAEliminar by remember { mutableStateOf<Coche?>(null) }

    // Diálogo de confirmación
    cocheAEliminar?.let { coche ->
        AlertDialog(
            onDismissRequest = { cocheAEliminar = null },
            title = { Text("Eliminar Coche") },
            text = { Text("¿Deseas eliminar el ${coche.name}?") },
            confirmButton = {
                TextButton(onClick = {
                    scope.launch { viewModel.delete(coche) }
                    cocheAEliminar = null
                }) { Text("Eliminar") }
            },
            dismissButton = {
                TextButton(onClick = { cocheAEliminar = null }) { Text("Cancelar") }
            }
        )
    }

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Button(
            onClick = {
                viewModel.resetForm()
                navController.navigate("crear")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null // El texto de al lado ya describe la acción
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing)) // Espacio estándar entre icono y texto
            Text("Añadir Coche")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(coches) { coche ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = coche.name, style = MaterialTheme.typography.titleLarge)
                            Text(text = "Precio: ${coche.price}€ | Stock: ${coche.quantity}")
                        }
                        Row {
                            // Icono de Ojo para "Ver" o "Detalles"
                            IconButton(onClick = { /* Tu lógica para ver detalles */ }) {
                                Icon(
                                    imageVector = Icons.Default.Visibility,
                                    contentDescription = "Ver detalles"
                                )
                            }

                            // Icono de Lápiz para "Editar"
                            IconButton(onClick = { navController.navigate("editar/${coche.id}") }) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Editar coche"
                                )
                            }

                            // Icono de Papelera para "Eliminar"
                            IconButton(onClick = { cocheAEliminar = coche }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Eliminar coche",
                                    tint = MaterialTheme.colorScheme.error // Esto lo pone en rojo
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}