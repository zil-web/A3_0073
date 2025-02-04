package com.example.uaspam.ui.ViewModel

import PemasokRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Kategori
import com.example.uaspam.model.Merk
import com.example.uaspam.model.Pemasok
import com.example.uaspam.model.Produk
import com.example.uaspam.repository.KategoriRepository
import com.example.uaspam.repository.MerkRepository
import com.example.uaspam.repository.ProdukRepository
import kotlinx.coroutines.launch

class InsertViewModel(

    private val prdk: ProdukRepository,
    private val pmsk: PemasokRepository,
    private val ktgr: KategoriRepository,
    private val mrk: MerkRepository,

    ): ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
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

    fun updateInsertprdkState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent)
    }


    suspend fun insertprdk(){
        viewModelScope.launch {
            try {
                prdk.insertproduk(uiState.insertUiEvent.toprdk())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}

fun InsertUiEvent.toprdk(): Produk = Produk(
    id_produk = id_produk ?: 0,
    nama_produk = nama_produk,
    deskripsi_produk = deskripsi_produk,
    harga = harga ?: 0,
    stok = stok ?: 0,
    id_kategori = id_kategori ?: 0,
    id_pemasok = id_pemasok ?: 0,
    id_merk = id_merk ?: 0,
)

fun Produk.toUiState(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Produk.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    id_produk = id_produk,
    nama_produk = nama_produk,
    deskripsi_produk = deskripsi_produk,
    harga = harga,
    stok = stok,
    id_kategori = id_kategori,
    id_pemasok = id_pemasok,
    id_merk = id_merk,

)

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val id_produk: Int? = null,
    val nama_produk: String = "",
    val deskripsi_produk: String = "",
    val harga:Int? = null,
    val stok: Int? = null,
    val id_kategori: Int? = null,
    val id_pemasok: Int? = null,
    val id_merk: Int? = null,

    )



