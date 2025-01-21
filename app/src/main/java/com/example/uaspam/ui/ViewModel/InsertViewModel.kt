package com.example.uaspam.ui.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Produk
import com.example.uaspam.repository.ProdukRepository
import kotlinx.coroutines.launch

class InsertViewModel(private val prdk: ProdukRepository): ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertprdkState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent)
    }

    suspend fun insertprdk(){
        viewModelScope.launch {
            try {
                prdk.insertProduk(uiState.insertUiEvent.toprdk())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}

fun InsertUiEvent.toprdk(): Produk = Produk(
    id_produk = id_produk,
    nama_produk = nama_produk,
    deskripsi_produk = deskripsi_produk,
    stok = stok,
    id_kategori = id_kategori,
    id_pemasok = id_pemasok,
    id_merk = id_merk,
)

fun Produk.toUiStateprdk(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Produk.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    id_produk = id_produk,
    nama_produk = nama_produk,
    deskripsi_produk = deskripsi_produk,
    stok = stok,
    id_kategori = id_kategori,
    id_pemasok = id_pemasok,
    id_merk = id_merk,
)

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val id_produk: String = "",
    val nama_produk: String = "",
    val deskripsi_produk: String = "",
    val stok: String = "",
    val id_kategori: String = "",
    val id_pemasok: String = "",
    val id_merk: String = "",

    )