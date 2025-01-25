package com.example.uaspam.ui.ViewModelKategori

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Kategori
import com.example.uaspam.repository.KategoriRepository
import kotlinx.coroutines.launch

class InsertViewModelKategori(private val ktgr: KategoriRepository): ViewModel() {
    var uiStateKategori by mutableStateOf(InsertUiStateKategori())
        private set

    fun updateInsertktgrState(insertUiEventKategori: InsertUiEventKategori){
        uiStateKategori = InsertUiStateKategori(insertUiEventKategori)
    }


    suspend fun insertktgr(){
        viewModelScope.launch {
            try {
                ktgr.insertkategori(uiStateKategori.insertUiEventKategori.toktgr())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}

fun InsertUiEventKategori.toktgr(): Kategori = Kategori(
    id_kategori = id_kategori,
    nama_kategori = nama_kategori,
    deskripsi_kategori = deskripsi_kategori,


)

fun Kategori.toUiStateKategori(): InsertUiStateKategori = InsertUiStateKategori(
    insertUiEventKategori = toInsertUiEventKategori()
)

fun Kategori.toInsertUiEventKategori(): InsertUiEventKategori = InsertUiEventKategori(
    id_kategori = id_kategori,
    nama_kategori = nama_kategori,
    deskripsi_kategori = deskripsi_kategori,

    )

data class InsertUiStateKategori(
    val insertUiEventKategori: InsertUiEventKategori = InsertUiEventKategori()
)

data class InsertUiEventKategori(
    val id_kategori: String = "",
    val nama_kategori: String = "",
    val deskripsi_kategori: String = "",

    )