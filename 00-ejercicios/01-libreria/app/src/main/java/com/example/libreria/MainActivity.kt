package com.example.libreria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.libreria.ui.theme.LibreriaTheme
import com.example.libreria.ventanas.VentanaEditar
import com.example.libreria.ventanas.VentanaVer
import com.example.libreria.ventanas.VentanaCrear

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LibreriaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    gestorVentanas(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun gestorVentanas(modifier: Modifier) {
    val libreriaViewModel: LibreriaViewModel = viewModel()

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "inicio") {
        composable("ver") { VentanaVer(navController, modifier, libreriaViewModel) }
        composable("editar") { VentanaEditar(navController, modifier, libreriaViewModel ) }
        composable("crear") { VentanaCrear(navController, modifier, libreriaViewModel) }

    }


}