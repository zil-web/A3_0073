package com.example.uaspam.ui.ViewmodelMerk

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Merk
import com.example.uaspam.repository.MerkRepository
import kotlinx.coroutines.launch

class InsertViewModelMerk(private val mrk: MerkRepository): ViewModel() {
    var uiStateMerk by mutableStateOf(InsertUiStateMerk())
        private set

    fun updateInsertmrkState(insertUiEventMerk: InsertUiEventMerk){
        uiStateMerk = InsertUiStateMerk(insertUiEventMerk)
    }


    suspend fun insertmrk(){
        viewModelScope.launch {
            try {
                mrk.insertmerk(uiStateMerk.insertUiEventMerk.tomrk())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}

fun InsertUiEventMerk.tomrk(): Merk = Merk(
    id_merk = id_merk ?: 0,
    nama_merk = nama_merk,
    deskripsi_merk = deskripsi_merk,


)

fun Merk.toUiStateMerk(): InsertUiStateMerk = InsertUiStateMerk(
    insertUiEventMerk = toInsertUiEventMerk()
)

fun Merk.toInsertUiEventMerk(): InsertUiEventMerk = InsertUiEventMerk(
    id_merk = id_merk,
    nama_merk = nama_merk,
    deskripsi_merk = deskripsi_merk,

    )

data class InsertUiStateMerk(
    val insertUiEventMerk: InsertUiEventMerk = InsertUiEventMerk()
)

data class InsertUiEventMerk(
    val id_merk: Int? = null,
    val nama_merk: String = "",
    val deskripsi_merk: String = "",

    )