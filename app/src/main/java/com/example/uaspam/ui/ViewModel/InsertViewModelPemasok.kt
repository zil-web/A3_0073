package com.example.uaspam.ui.ViewModel

import PemasokRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Pemasok
import kotlinx.coroutines.launch

class InsertViewModelPemasok(private val pmsk: PemasokRepository): ViewModel() {
    var uiStatePemasok by mutableStateOf(InsertUiStatePemasok())
        private set

    fun updateInsertpmskState(insertUiEventPemasok: InsertUiEventPemasok){
        uiStatePemasok = InsertUiStatePemasok(insertUiEventPemasok)
    }


    suspend fun insertpmsk(){
        viewModelScope.launch {
            try {
                pmsk.insertpemasok(uiStatePemasok.insertUiEventPemasok.topmsk())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}

fun InsertUiEventPemasok.topmsk(): Pemasok = Pemasok(
    id_pemasok = id_pemasok ?: 0,
    nama_produk = nama_produk,
    nama_pemasok = nama_pemasok,
    alamat_pemasok = alamat_pemasok,
    telepon_pemasok = telepon_pemasok,
    
)

fun Pemasok.toUiStatePemasok(): InsertUiStatePemasok = InsertUiStatePemasok(
    insertUiEventPemasok = toInsertUiEventPemasok()
)

fun Pemasok.toInsertUiEventPemasok(): InsertUiEventPemasok = InsertUiEventPemasok(
    id_pemasok = id_pemasok,
    nama_produk = nama_produk,
    nama_pemasok = nama_pemasok,
    alamat_pemasok = alamat_pemasok,
    telepon_pemasok = telepon_pemasok,

    )

data class InsertUiStatePemasok(
    val insertUiEventPemasok: InsertUiEventPemasok = InsertUiEventPemasok()
)

data class InsertUiEventPemasok(
    val id_pemasok: Int? = null,
    val nama_produk: String = "",
    val nama_pemasok: String = "",
    val alamat_pemasok: String = "",
    val telepon_pemasok: String = "",

    )