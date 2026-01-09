package com.example.libreria.ventanas

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.libreria.LibreriaViewModel

@Composable
fun VentanaCrear(navController: NavController,modifier: Modifier, libreriaViewModel: LibreriaViewModel) {

    val context = LocalContext.current

    DefaultColumn {
        Text(text = "Gestión de Libros")
        Button(onClick = { libreriaViewModel.allLibros }) {
            Text("Mostrar todos")
        }
    }
}

