package com.example.uaspam.ui.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.produkResponseDetail
import com.example.uaspam.repository.ProdukRepository
import com.example.uaspam.ui.View.DestinasiDetail
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed class DetailUiState {
    data class Success(val produk: produkResponseDetail) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val prdk: ProdukRepository
) : ViewModel() {

    var produkDetailState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    private val _id_produk: String = checkNotNull(savedStateHandle[DestinasiDetail.ID_produk])

    init {
        getprodukbyid_produk()
    }

    fun getprodukbyid_produk() {
        viewModelScope.launch {
            produkDetailState = DetailUiState.Loading
            produkDetailState = try {
                val produk = prdk.getprodukByid_produk(_id_produk)
                DetailUiState.Success(produk )
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }
}

