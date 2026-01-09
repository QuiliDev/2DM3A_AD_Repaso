package com.example.libreria

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class LibreriaViewModel(application: Application) : AndroidViewModel(application) {

    private val libroDao = LibreriaDatabase.getDatabase(application).libroDao()

    fun insertLibro(titulo: String, autor: String, published: Int) {
        val newLibro = Libro(titulo = titulo, autor = autor, published = published)
        MyLog.d( "Nuevo Libro creado: $newLibro")

        viewModelScope.launch(Dispatchers.IO) {
            libroDao.insert(newLibro)
            MyLog.d("Insert realizado")
        }
    }

    fun getLibro(id: Int): Flow<Libro> {
        return libroDao.getLibro(id)
    }

    val allLibros: Flow<List<Libro>> = libroDao.getAllLibros()

    suspend fun delete(libro: Libro){
        libroDao.delete(libro)
    }


    suspend fun update(libro: Libro){
        libroDao.update(libro)
    }

}