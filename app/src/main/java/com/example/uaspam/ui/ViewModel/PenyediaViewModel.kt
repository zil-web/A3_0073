package com.example.uaspam.ui.ViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.uaspam.tokoApplication

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { HomeViewModel(prdkApp().container.produkRepository) }
        initializer { InsertViewModel(prdkApp().container.produkRepository) }
        initializer { DetailViewModel(createSavedStateHandle(),prdkApp().container.produkRepository)}
        initializer { UpdateViewModel(createSavedStateHandle(),prdkApp().container.produkRepository)}
    }
}

fun CreationExtras.prdkApp(): tokoApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as tokoApplication)