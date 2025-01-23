package com.example.uaspam.ui.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Produk
import com.example.uaspam.repository.ProdukRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeUiState{
    data class Success(val produk: List<Produk>): HomeUiState()
    object Error: HomeUiState()
    object Loading: HomeUiState()
}

class HomeViewModel(private val prdk: ProdukRepository): ViewModel(){
    var prdkUiState: HomeUiState by  mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getprdk()
    }

    fun getprdk() {
        viewModelScope.launch {
            prdkUiState = HomeUiState.Loading
            prdkUiState = try {
                HomeUiState.Success(prdk.getproduk().data)
            } catch (e: IOException) {
                HomeUiState.Error
            }catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    fun deleteprdk(id_produk: String){
        viewModelScope.launch {
            try {
                prdk.deleteproduk(id_produk)
            }catch (e: Exception){
                HomeUiState.Error
            }catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }
}