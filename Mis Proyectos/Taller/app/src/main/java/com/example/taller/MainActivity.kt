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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TallerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    gestorVentanas(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun gestorVentanas(modifier: Modifier) {
    val context = LocalContext.current

    val repositorio = TallerRepository(TallerDatabase.getDatabase(context).cocheDao())

    val factory = object : androidx.lifecycle.ViewModelProvider.Factory {
        override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
            return CocheViewModel(repositorio) as T
        }
    }

    val cocheViewModel: CocheViewModel = viewModel(factory = factory)

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "ver") {

        composable(
            route = "editar/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            VentanaEditar(navController, modifier, cocheViewModel, id)
        }

        composable("ver") { VentanaVer(navController, modifier, cocheViewModel) }
        composable("crear") { VentanaCrear(navController, modifier, cocheViewModel) }
    }
}