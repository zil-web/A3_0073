package com.example.uaspam.ui.ViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.uaspam.tokoApplication
import com.example.uaspam.ui.ViewModelKategori.DetailViewModelKategori
import com.example.uaspam.ui.ViewModelKategori.HomeViewModelKategori
import com.example.uaspam.ui.ViewModelKategori.InsertViewModelKategori
import com.example.uaspam.ui.ViewModelKategori.UpdateViewModelKategori

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { HomeViewModel(prdkApp().container.produkRepository) }
        initializer { InsertViewModel(prdkApp().container.produkRepository) }
        initializer { DetailViewModel(createSavedStateHandle(),prdkApp().container.produkRepository)}
        initializer { UpdateViewModel(createSavedStateHandle(),prdkApp().container.produkRepository)}

        initializer { HomeViewModelPemasok(prdkApp().container.pemasokRepository) }
        initializer { InsertViewModelPemasok(prdkApp().container.pemasokRepository) }
        initializer { DetailViewModelPemasok(createSavedStateHandle(),prdkApp().container.pemasokRepository)}
        initializer { UpdateViewModelPemasok(createSavedStateHandle(),prdkApp().container.pemasokRepository)}

        initializer { HomeViewModelKategori(prdkApp().container.kategoriRepository) }
        initializer { InsertViewModelKategori(prdkApp().container.kategoriRepository) }
        initializer { DetailViewModelKategori(createSavedStateHandle(),prdkApp().container.kategoriRepository) }
        initializer { UpdateViewModelKategori(createSavedStateHandle(),prdkApp().container.kategoriRepository) }
    }
}

fun CreationExtras.prdkApp(): tokoApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as tokoApplication)