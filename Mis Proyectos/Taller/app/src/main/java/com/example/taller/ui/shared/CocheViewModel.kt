package com.example.taller.ui.shared

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taller.data.TallerRepository
import com.example.taller.entity.Coche
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CocheViewModel(
    private val repositorio: TallerRepository
) : ViewModel() {

    // Estados para los campos del formulario
    var nameState by mutableStateOf("")
    var priceState by mutableStateOf("")
    var quantityState by mutableStateOf("")

    // Funciones para actualizar los estados
    fun onNameChange(newValue: String) { nameState = newValue }
    fun onPriceChange(newValue: String) { priceState = newValue }
    fun onQuantityChange(newValue: String) { quantityState = newValue }

    fun resetForm() {
        nameState = ""
        priceState = ""
        quantityState = ""
    }

    fun cargarCoche(coche: Coche) {
        nameState = coche.name
        priceState = coche.price.toString()
        quantityState = coche.quantity.toString()
    }

    fun insertCoche(name: String, price: Double, quantity: Int) {
        val newCoche = Coche(name = name, price = price, quantity = quantity)
        viewModelScope.launch(Dispatchers.IO) {
            repositorio.insertarCoche(newCoche)
        }
    }

    fun getCoche(id: Int): Flow<Coche> = repositorio.getCoche(id)

    val allCoches: Flow<List<Coche>> = repositorio.todosLosCoches

    fun delete(coche: Coche) {
        viewModelScope.launch(Dispatchers.IO) {
            repositorio.eliminarCoche(coche)
        }
    }

    suspend fun update(coche: Coche) {
        repositorio.actualizarCoche(coche)
    }
}