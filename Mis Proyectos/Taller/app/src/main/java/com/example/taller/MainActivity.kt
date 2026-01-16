package com.example.taller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taller.data.TallerDatabase
import com.example.taller.data.TallerRepository
import com.example.taller.ui.theme.TallerTheme
import com.example.taller.ui.shared.CocheViewModel
import com.example.taller.ui.ventanas.VentanaEditar
import com.example.taller.ui.ventanas.VentanaVer
import com.example.taller.ui.ventanas.VentanaCrear
import com.example.taller.ui.ventanas.VentanaCrearReparacion
import com.example.taller.ui.ventanas.VentanaCrearVehiculo
import com.example.taller.ui.ventanas.VentanaVerReparaciones
import com.example.taller.ui.ventanas.VentanaVerVehiculos

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TallerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GestorVentanas(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GestorVentanas(modifier: Modifier) {
    val context = LocalContext.current
    val db = TallerDatabase.getDatabase(context)

    val repositorio = TallerRepository(
        clienteDao = db.clienteDao(),
        vehiculoDao = db.vehiculoDao(),
        reparacionDao = db.reparacionDao()
    )

    val factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CocheViewModel(repositorio) as T
        }
    }

    val cocheViewModel: CocheViewModel = viewModel(factory = factory)
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "ver") {
        composable("ver") {
            VentanaVer(navController, modifier, cocheViewModel)
        }

        composable("crear") {
            VentanaCrear(navController, modifier, cocheViewModel)
        }

        composable(
            route = "editar/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            VentanaEditar(navController, modifier, cocheViewModel, id)
        }

        composable(
            route = "crear_vehiculo/{idCliente}",
            arguments = listOf(navArgument("idCliente") { type = NavType.IntType })
        ) { backStackEntry ->
            val idCliente = backStackEntry.arguments?.getInt("idCliente") ?: 0
            VentanaCrearVehiculo(navController, modifier, cocheViewModel, idCliente)
        }

        composable(
            route = "ver_vehiculos/{idCliente}",
            arguments = listOf(navArgument("idCliente") { type = NavType.IntType })
        ) { backStackEntry ->
            val idCliente = backStackEntry.arguments?.getInt("idCliente") ?: 0
            VentanaVerVehiculos(navController, modifier, cocheViewModel, idCliente)
        }

        composable(
            route = "ver_reparaciones/{idVehiculo}",
            arguments = listOf(navArgument("idVehiculo") { type = NavType.IntType })
        ) { backStackEntry ->
            val idVehiculo = backStackEntry.arguments?.getInt("idVehiculo") ?: 0
            VentanaVerReparaciones(navController, modifier, cocheViewModel, idVehiculo)
        }

        composable(
            route = "crear_reparacion/{idVehiculo}",
            arguments = listOf(navArgument("idVehiculo") { type = NavType.IntType })
        ) { backStackEntry ->
            val idVehiculo = backStackEntry.arguments?.getInt("idVehiculo") ?: 0
            VentanaCrearReparacion(navController, modifier, cocheViewModel, idVehiculo)
        }
    }
}