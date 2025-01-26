package com.example.uaspam.ui.ViewmodelMerk

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Merk
import com.example.uaspam.repository.MerkRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeUiStateMerk{
    data class Success(val merk: List<Merk>): HomeUiStateMerk()
    object Error: HomeUiStateMerk()
    object Loading: HomeUiStateMerk()
}

class HomeViewModelMerk(private val mrk: MerkRepository): ViewModel(){
    var mrkUiState: HomeUiStateMerk by  mutableStateOf(HomeUiStateMerk.Loading)
        private set

    init {
        getmrk()
    }

    fun getmrk() {
        viewModelScope.launch {
            mrkUiState = HomeUiStateMerk.Loading
            mrkUiState = try {
                HomeUiStateMerk.Success(mrk.getmerk().data)
            } catch (e: IOException) {
                HomeUiStateMerk.Error
            }catch (e: HttpException) {
                HomeUiStateMerk.Error
            }
        }
    }

    fun deletemrk(id_merk: String){
        viewModelScope.launch {
            try {
                mrk.deletemerk(id_merk)
            }catch (e: Exception){
                HomeUiStateMerk.Error
            }catch (e: HttpException) {
                HomeUiStateMerk.Error
            }
        }
    }
}