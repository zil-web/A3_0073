package com.example.uaspam.ui.ViewModel

import PemasokRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.pemasokResponseDetail
import com.example.uaspam.ui.Navigation.DestinasiDetailPemasok
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class DetailUiStatePemasok {
    data class Success(val pemasok: pemasokResponseDetail) : DetailUiStatePemasok()
    object Error : DetailUiStatePemasok()
    object Loading : DetailUiStatePemasok()
}

class DetailViewModelPemasok(
    savedStateHandle: SavedStateHandle,
    private val pmsk: PemasokRepository
) : ViewModel() {

    var pemasokDetailState: DetailUiStatePemasok by mutableStateOf(DetailUiStatePemasok.Loading)
        private set

    private val _id_pemasok: Int = checkNotNull(savedStateHandle[DestinasiDetailPemasok.ID_pemasok])

    init {
        getpemasokbyid_pemasok()
    }

    fun getpemasokbyid_pemasok() {
        viewModelScope.launch {
            pemasokDetailState = DetailUiStatePemasok.Loading
            pemasokDetailState = try {
                val pemasok = pmsk.getpemasokByid_pemasok(_id_pemasok)
                DetailUiStatePemasok.Success(pemasok )
            } catch (e: IOException) {
                DetailUiStatePemasok.Error
            } catch (e: HttpException) {
                DetailUiStatePemasok.Error
            }
        }
    }
}