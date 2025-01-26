package com.example.uaspam.ui.ViewmodelMerk

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.repository.MerkRepository
import com.example.uaspam.ui.Navigation.DestinasiUpdateMerk
import kotlinx.coroutines.launch

class UpdateViewModelMerk(
    savedStateHandle: SavedStateHandle,
    private val merkRepository: MerkRepository
): ViewModel(){
    var updateUiStatemerk by mutableStateOf(InsertUiStateMerk())
        private set

    private val _id_merk: String = checkNotNull(savedStateHandle[DestinasiUpdateMerk.ID_merk])

    init {
        viewModelScope.launch {
            updateUiStatemerk = merkRepository.getmerkByid_merk(_id_merk).data
                .toUiStateMerk()
        }
    }

    fun updateInsertmrkState(insertUiEventMerk: InsertUiEventMerk){
        updateUiStatemerk = InsertUiStateMerk(insertUiEventMerk = insertUiEventMerk)
    }

    suspend fun updatemrk(){
        viewModelScope.launch {
            try {
                merkRepository.updatemerk(_id_merk, updateUiStatemerk.insertUiEventMerk.tomrk())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}
