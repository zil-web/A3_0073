package com.example.uaspam.ui.ViewmodelMerk

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.merkResponseDetail
import com.example.uaspam.repository.MerkRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class DetailUiStateMerk {
    data class Success(val merk: merkResponseDetail) : DetailUiStateMerk()
    object Error : DetailUiStateMerk()
    object Loading : DetailUiStateMerk()
}

class DetailViewModelMerk(
    savedStateHandle: SavedStateHandle,
    private val ktgr: MerkRepository
) : ViewModel() {

    var merkDetailState: DetailUiStateMerk by mutableStateOf(DetailUiStateMerk.Loading)
        private set

    private val _id_merk: String = checkNotNull(savedStateHandle[DestinasiDetailMerk.ID_merk])

    init {
        getmerkbyid_merk()
    }

    fun getmerkbyid_merk() {
        viewModelScope.launch {
            merkDetailState = DetailUiStateMerk.Loading
            merkDetailState = try {
                val merk = ktgr.getmerkByid_merk(_id_merk)
                DetailUiStateMerk.Success(merk )
            } catch (e: IOException) {
                DetailUiStateMerk.Error
            } catch (e: HttpException) {
                DetailUiStateMerk.Error
            }
        }
    }
}