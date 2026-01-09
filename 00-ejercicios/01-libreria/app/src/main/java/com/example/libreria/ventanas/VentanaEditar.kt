package com.example.libreria.ventanas

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.libreria.LibreriaViewModel

@Composable
fun VentanaEditar(navController: NavController,modifier: Modifier, libreriaViewModel: LibreriaViewModel) {

    val context = LocalContext.current

    var titulo by remember { mutableStateOf("") }
    var autor by remember { mutableStateOf("") }

    DefaultColumn {
        DefaultOutlinedTextField(
            texto = titulo,
            onTextoChange = { titulo = it },
            placeholder = "Titulo"
        )
        DefaultOutlinedTextField(
            texto = autor,
            onTextoChange = { autor = it },
            placeholder = "Autor"
        )

    }
}