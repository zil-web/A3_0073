package com.example.uaspam.ui.ViewKategori


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.ui.Customwidget.CustomTopAppBar
import com.example.uaspam.ui.Navigation.DestinasiUpdateKategori
import com.example.uaspam.ui.ViewModel.PenyediaViewModel
import com.example.uaspam.ui.ViewModel.UpdateViewModel
import com.example.uaspam.ui.ViewModelKategori.UpdateViewModelKategori
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreenKategori(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate:()-> Unit,
    viewModel: UpdateViewModelKategori = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiUpdateKategori.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ){padding ->
        EntryBodyKategori(
            modifier = Modifier.padding(padding),
            InsertUiStateKategori = viewModel.updateUiStatekategori,
            onValueChange = viewModel::updateInsertktgrState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updatektgr()
                    delay(600)
                    withContext(Dispatchers.Main){
                        onNavigate()
                    }
                }
            }
        )
    }
}