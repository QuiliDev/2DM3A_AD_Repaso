package com.example.taller.ui.ventanas

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.taller.ui.components.CocheForm
import com.example.taller.ui.shared.CocheViewModel

@Composable
fun VentanaCrear(navController: NavController, modifier: Modifier, viewModel: CocheViewModel) {
    CocheForm(
        labelVentana = "Añadir Nuevo Coche",
        name = viewModel.nameState,
        price = viewModel.priceState,
        quantity = viewModel.quantityState,
        onNameChange = { viewModel.onNameChange(it) },
        onPriceChange = { viewModel.onPriceChange(it) },
        onQuantityChange = { viewModel.onQuantityChange(it) },
        onAceptarClick = {
            viewModel.insertCoche(
                viewModel.nameState,
                viewModel.priceState.toDoubleOrNull() ?: 0.0,
                viewModel.quantityState.toIntOrNull() ?: 0
            )
            navController.popBackStack()
        },
        onCancelarClick = { navController.popBackStack() }
    )
}