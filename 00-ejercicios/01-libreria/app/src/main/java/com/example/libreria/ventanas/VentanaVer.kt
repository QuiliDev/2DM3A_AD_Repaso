package com.example.libreria.ventanas

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.libreria.LibreriaViewModel
import com.example.libreria.Libro

@Composable
fun VentanaVer(navController: NavController, modifier: Modifier, libreriaViewModel: LibreriaViewModel) {
    DefaultColumn(modifier = modifier) {
        Button({navController.navigate("inicio")}) { Text("Añadir libro") }
    }
    val libros by libreriaViewModel.allLibros.collectAsState(initial = emptyList())

    // Muestra la lista
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(libros) { libro ->
            LibroItem(libro)
        }
    }
}

@Composable
fun LibroItem(libro: Libro) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        DefaultColumn {
            Text(text = libro.titulo, style = MaterialTheme.typography.titleMedium)
            Text(text = "Autor: ${libro.autor}")
            Text(text = "Año: ${libro.published}")
        }
    }
}