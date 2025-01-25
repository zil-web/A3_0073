package com.example.uaspam.ui.ViewModelKategori

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Kategori
import com.example.uaspam.repository.KategoriRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeUiStateKategori{
    data class Success(val kategori: List<Kategori>): HomeUiStateKategori()
    object Error: HomeUiStateKategori()
    object Loading: HomeUiStateKategori()
}

class HomeViewModelKategori(private val ktgr: KategoriRepository): ViewModel(){
    var ktgrUiState: HomeUiStateKategori by  mutableStateOf(HomeUiStateKategori.Loading)
        private set

    init {
        getktgr()
    }

    fun getktgr() {
        viewModelScope.launch {
            ktgrUiState = HomeUiStateKategori.Loading
            ktgrUiState = try {
                HomeUiStateKategori.Success(ktgr.getkategori().data)
            } catch (e: IOException) {
                HomeUiStateKategori.Error
            }catch (e: HttpException) {
                HomeUiStateKategori.Error
            }
        }
    }

    fun deletektgr(id_kategori: String){
        viewModelScope.launch {
            try {
                ktgr.deletekategori(id_kategori)
            }catch (e: Exception){
                HomeUiStateKategori.Error
            }catch (e: HttpException) {
                HomeUiStateKategori.Error
            }
        }
    }
}