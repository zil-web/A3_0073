package com.example.uaspam.ui.ViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(prdkApp().container.produkRepository)
        }
        initializer {
            InsertViewModel(prdkApp().container.produkRepository)
        }
    }
}

fun CreationExtras.prdkApp(): ProdukApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as ProdukApplication)