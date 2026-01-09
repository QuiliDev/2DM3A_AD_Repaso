package com.example.libreria.ventanas

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun DefaultOutlinedTextField(
    texto: String,
    onTextoChange: (String) -> Unit,
    placeholder: String = "Buscar por título o autor"
) {
    OutlinedTextField(
        value = texto,
        onValueChange = onTextoChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        placeholder = { Text(placeholder) },
        singleLine = true
    )
}