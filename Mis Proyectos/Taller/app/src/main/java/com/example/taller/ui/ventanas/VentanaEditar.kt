package com.example.taller.ui.ventanas

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.taller.entity.Coche
import com.example.taller.ui.components.CocheForm
import com.example.taller.ui.shared.CocheViewModel
import kotlinx.coroutines.launch

@Composable
fun VentanaEditar(navController: NavController, modifier: Modifier, viewModel: CocheViewModel, id: Int) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(id) {
        viewModel.getCoche(id).collect { coche ->
            coche?.let { viewModel.cargarCoche(it) }
        }
    }

    CocheForm(
        labelVentana = "Editar Coche Existente",
        name = viewModel.nameState,
        price = viewModel.priceState,
        quantity = viewModel.quantityState,
        onNameChange = { viewModel.onNameChange(it) },
        onPriceChange = { viewModel.onPriceChange(it) },
        onQuantityChange = { viewModel.onQuantityChange(it) },
        onAceptarClick = {
            scope.launch {
                val cocheEditado = Coche(
                    id = id,
                    name = viewModel.nameState,
                    price = viewModel.priceState.toDoubleOrNull() ?: 0.0,
                    quantity = viewModel.quantityState.toIntOrNull() ?: 0
                )
                viewModel.update(cocheEditado)
                navController.popBackStack()
            }
        },
        onCancelarClick = { navController.popBackStack() }
    )
}