package com.example.uaspam.ui.ViewModelKategori

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.repository.KategoriRepository
import com.example.uaspam.ui.Navigation.DestinasiUpdateKategori
import kotlinx.coroutines.launch

class UpdateViewModelKategori(
    savedStateHandle: SavedStateHandle,
    private val kategoriRepository: KategoriRepository
): ViewModel(){
    var updateUiStatekategori by mutableStateOf(InsertUiStateKategori())
        private set

    private val _id_kategori: String = checkNotNull(savedStateHandle[DestinasiUpdateKategori.ID_kategori])

    init {
        viewModelScope.launch {
            updateUiStatekategori = kategoriRepository.getkategoriByid_kategori(_id_kategori).data
                .toUiStateKategori()
        }
    }

    fun updateInsertktgrState(insertUiEventKategori: InsertUiEventKategori){
        updateUiStatekategori = InsertUiStateKategori(insertUiEventKategori = insertUiEventKategori)
    }

    suspend fun updatektgr(){
        viewModelScope.launch {
            try {
                kategoriRepository.updatekategori(_id_kategori, updateUiStatekategori.insertUiEventKategori.toktgr())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}
