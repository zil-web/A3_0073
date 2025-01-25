package com.example.uaspam.ui.ViewModel

import PemasokRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Pemasok
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeUiStatePemasok{
    data class Success(val pemasok: List<Pemasok>): HomeUiStatePemasok()
    object Error: HomeUiStatePemasok()
    object Loading: HomeUiStatePemasok()
}

class HomeViewModelPemasok(private val pmsk: PemasokRepository): ViewModel(){
    var pmskUiState: HomeUiStatePemasok by  mutableStateOf(HomeUiStatePemasok.Loading)
        private set

    init {
        getpmsk()
    }

    fun getpmsk() {
        viewModelScope.launch {
            pmskUiState = HomeUiStatePemasok.Loading
            pmskUiState = try {
                HomeUiStatePemasok.Success(pmsk.getpemasok().data)
            } catch (e: IOException) {
                HomeUiStatePemasok.Error
            }catch (e: HttpException) {
                HomeUiStatePemasok.Error
            }
        }
    }

    fun deletepmsk(id_pemasok: String){
        viewModelScope.launch {
            try {
                pmsk.deletepemasok(id_pemasok)
            }catch (e: Exception){
                HomeUiStatePemasok.Error
            }catch (e: HttpException) {
                HomeUiStatePemasok.Error
            }
        }
    }
}