package com.example.taller.ui.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize
import com.example.taller.entity.Coche

@Composable
fun CocheDropdown(
    listaCoches: List<Coche>,
    idCocheSeleccionado: Int?, // Recibimos el ID real de la base de datos
    onCocheSeleccionado: (Coche) -> Unit, // Devolvemos el objeto Coche completo al seleccionar
    label: String = "Seleccionar Coche"
) {
    var expanded by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val density = LocalDensity.current

    // Buscamos el nombre del coche actual basándonos en el ID seleccionado
    val cocheActualNombre = listaCoches.find { it.id == idCocheSeleccionado }?.name ?: ""

    // Filtrado por nombre (case-insensitive)
    val filtrados = remember(query, listaCoches) {
        val q = query.trim().lowercase()
        if (q.isEmpty()) listaCoches else listaCoches.filter { it.name.lowercase().contains(q) }
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = cocheActualNombre,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Abrir menú")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { textFieldSize = it.size.toSize() }
                .clickable { expanded = true }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(density) { textFieldSize.width.toDp() })
        ) {
            // Barra de búsqueda dentro del menú
            DropdownMenuItem(
                text = {
                    OutlinedTextField(
                        value = query,
                        onValueChange = { query = it },
                        placeholder = { Text("Buscar modelo…") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                onClick = {},
                enabled = false
            )

            if (filtrados.isEmpty()) {
                DropdownMenuItem(
                    text = { Text("Sin resultados") },
                    onClick = {},
                    enabled = false
                )
            } else {
                filtrados.forEach { coche ->
                    DropdownMenuItem(
                        text = { Text("${coche.name} (${coche.price}€)") },
                        onClick = {
                            Log.d("CocheDropdown", "Seleccionado: ${coche.name}")
                            onCocheSeleccionado(coche)
                            expanded = false
                            query = ""
                        }
                    )
                }
            }
        }
    }
}