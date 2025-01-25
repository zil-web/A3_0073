package com.example.uaspam.ui.ViewModelKategori

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.kategoriResponseDetail
import com.example.uaspam.repository.KategoriRepository
import com.example.uaspam.ui.Navigation.DestinasiDetailKategori
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class DetailUiStateKategori {
    data class Success(val kategori: kategoriResponseDetail) : DetailUiStateKategori()
    object Error : DetailUiStateKategori()
    object Loading : DetailUiStateKategori()
}

class DetailViewModelKategori(
    savedStateHandle: SavedStateHandle,
    private val ktgr: KategoriRepository
) : ViewModel() {

    var kategoriDetailState: DetailUiStateKategori by mutableStateOf(DetailUiStateKategori.Loading)
        private set

    private val _id_kategori: String = checkNotNull(savedStateHandle[DestinasiDetailKategori.ID_kategori])

    init {
        getkategoribyid_kategori()
    }

    fun getkategoribyid_kategori() {
        viewModelScope.launch {
            kategoriDetailState = DetailUiStateKategori.Loading
            kategoriDetailState = try {
                val kategori = ktgr.getkategoriByid_kategori(_id_kategori)
                DetailUiStateKategori.Success(kategori )
            } catch (e: IOException) {
                DetailUiStateKategori.Error
            } catch (e: HttpException) {
                DetailUiStateKategori.Error
            }
        }
    }
}