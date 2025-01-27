package com.example.uaspam.ui.ViewModel

import PemasokRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Kategori
import com.example.uaspam.model.Merk
import com.example.uaspam.model.Pemasok
import com.example.uaspam.model.Produk
import com.example.uaspam.repository.KategoriRepository
import com.example.uaspam.repository.MerkRepository
import com.example.uaspam.repository.ProdukRepository
import com.example.uaspam.ui.Navigation.DestinasiUpdate
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UpdateViewModel(
    savedStateHandle: SavedStateHandle,
    private val produkRepository: ProdukRepository,
    private val pmsk: PemasokRepository,
    private val ktgr: KategoriRepository,
    private val mrk: MerkRepository


): ViewModel(){
    var updateUiState by mutableStateOf(InsertUiState())
        private set

    var pmskList by mutableStateOf<List<Pemasok>>(emptyList())
    var ktgrList by mutableStateOf<List<Kategori>>(emptyList())
    var mrkList by mutableStateOf<List<Merk>>(emptyList())


    init {
        //memuat data
        simpandata()
    }

    private fun simpandata() {
        viewModelScope.launch {
            try {
                pmskList = pmsk.getpemasok().data
                ktgrList = ktgr.getkategori().data
                mrkList = mrk.getmerk().data
            } catch (e: Exception) {

            }
        }
    }

    private val _id_produk: String = checkNotNull(savedStateHandle[DestinasiUpdate.ID_produk])

    init {
        viewModelScope.launch {
            updateUiState = produkRepository.getprodukByid_produk(_id_produk).data
                .toUiState()
        }
    }

    fun updateInsertprdkState(insertUiEvent: InsertUiEvent){
        updateUiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun updateprdk(){
        viewModelScope.launch {
            try {
                produkRepository.updateproduk(_id_produk, updateUiState.insertUiEvent.toprdk())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}
