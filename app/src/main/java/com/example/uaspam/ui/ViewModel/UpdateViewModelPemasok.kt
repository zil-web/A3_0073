package com.example.uaspam.ui.ViewModel

import PemasokRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.ui.Navigation.DestinasiUpdatePemasok
import kotlinx.coroutines.launch

class UpdateViewModelPemasok(
    savedStateHandle: SavedStateHandle,
    private val pemasokRepository: PemasokRepository
): ViewModel(){
    var updateUiStatepemasok by mutableStateOf(InsertUiStatePemasok())
        private set

    private val _id_pemasok: String = checkNotNull(savedStateHandle[DestinasiUpdatePemasok.ID_pemasok])

    init {
        viewModelScope.launch {
            updateUiStatepemasok = pemasokRepository.getpemasokByid_pemasok(_id_pemasok).data
                .toUiStatePemasok()
        }
    }

    fun updateInsertpmskState(insertUiEventPemasok: InsertUiEventPemasok){
        updateUiStatepemasok = InsertUiStatePemasok(insertUiEventPemasok = insertUiEventPemasok)
    }

    suspend fun updatepmsk(){
        viewModelScope.launch {
            try {
                pemasokRepository.updatepemasok(_id_pemasok, updateUiStatepemasok.insertUiEventPemasok.topmsk())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}
