package com.example.taller.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taller.data.entity.Cliente

@Composable
fun VehiculoForm(
    labelVentana: String,
    matricula: String,
    marca: String,
    modelo: String,
    anio: String,
    color: String,
    listaClientes: List<Cliente>, // Para el dropdown
    idClienteSeleccionado: Int?,
    onMatriculaChange: (String) -> Unit,
    onMarcaChange: (String) -> Unit,
    onModeloChange: (String) -> Unit,
    onAnioChange: (String) -> Unit,
    onColorChange: (String) -> Unit,
    onClienteSeleccionado: (Int) -> Unit,
    onAceptarClick: () -> Unit,
    onCancelarClick: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(labelVentana, style = MaterialTheme.typography.headlineSmall)

        // Dropdown para elegir al dueño
        Text("Seleccionar Cliente (Dueño):")
        CocheDropdown( // Reutilizamos el dropdown adaptado a Clientes
            listaCoches = emptyList(), // Aquí pasarías la lista de clientes
            idCocheSeleccionado = idClienteSeleccionado,
            onCocheSeleccionado = { cliente -> onClienteSeleccionado(cliente.id) }
        )

        OutlinedTextField(value = matricula, onValueChange = onMatriculaChange, label = { Text("Matrícula") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = marca, onValueChange = onMarcaChange, label = { Text("Marca") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = modelo, onValueChange = onModeloChange, label = { Text("Modelo") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = anio, onValueChange = onAnioChange, label = { Text("Año") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = color, onValueChange = onColorChange, label = { Text("Color") }, modifier = Modifier.fillMaxWidth())

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = onCancelarClick) { Text("Cancelar") }
            Button(onClick = onAceptarClick) { Text("Guardar Vehículo") }
        }
    }
}